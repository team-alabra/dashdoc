package com.dashdocnow.services;

import com.dashdocnow.DTO.ClientDTO;
import com.dashdocnow.DTO.SubscriptionDTO;
import com.dashdocnow.entities.*;
import com.dashdocnow.DTO.ProviderDTO;
import com.dashdocnow.interfaces.DashDocService;
import com.dashdocnow.interfaces.enums.SubscriptionStatus;
import com.dashdocnow.interfaces.enums.SubscriptionType;
import com.dashdocnow.interfaces.enums.UserType;
import com.dashdocnow.middleware.GlobalExceptionHandler;
import com.dashdocnow.repository.AgencyDAO;
import com.dashdocnow.repository.ProviderDAO;
import com.dashdocnow.utils.BadRequestException;
import com.dashdocnow.utils.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
// https://www.geeksforgeeks.org/spring-boot-transaction-management-using-transactional-annotation/
public class ProviderService implements DashDocService<ProviderDTO> {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private ProviderDAO providerDAO;
    @Autowired
    private AgencyDAO agencyDAO;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public ProviderDTO getById(Long id) {
        Provider myProvider = providerDAO.getById(id);
        if (myProvider == null) {
            throw new NotFoundException("No Provider with that email exists");
        }
        return myProvider.asDTO();
    }

    public ProviderDTO getByEmail(String email) {
        Provider myProvider = providerDAO.getByEmail(email);
        if (myProvider == null) {
            throw new NotFoundException("No Provider with that email exists");
        }
        return myProvider.asDTO();
    }

    @Override
    public ProviderDTO save(ProviderDTO provider) {
        try {
            var userType = provider.getUserType();
            var asProviderDBO = mapper.map(provider, Provider.class);
            var subscriptionType = provider.getUserType() == UserType.SOLE_PROVIDER ? SubscriptionType.INDIVIDUAL : SubscriptionType.AGENCY;
            var defaultSubscription = new Subscription(subscriptionType);

            // region Handle Agency Assignment
            if (provider.getAgencyID() != null
                    && userType == UserType.AGENCY_PROVIDER
                    || userType == UserType.AGENCY_ADMINISTRATOR) {
                Agency agency = agencyDAO.getById(provider.getAgencyID());
                asProviderDBO.setAgency(agency);
            }
            // endregion

            // region If AGENCY_PROVIDER, then set the ADMIN subscription to them
            if (userType == UserType.AGENCY_PROVIDER) {
                var agency = agencyDAO.getById(provider.getAgencyID());
                var agencyAdmin = providerDAO.getAgencyAdmin(agency.getId());

                asProviderDBO.setAgency(agency);

                defaultSubscription = agencyAdmin.getSubscription();
            }
            // endregion

            // Save user in database
            asProviderDBO.setSubscription(defaultSubscription);
            providerDAO.save(asProviderDBO);

            return asProviderDBO.asDTO();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public void delete(Long id) {
        providerDAO.delete(id);
    }

    public ProviderDTO cancelProviderSubscription(Long id) {
        try {
            Provider provider = providerDAO.getById(id);

            provider.getSubscription().setStatus(SubscriptionStatus.valueOf("INACTIVE"));

            providerDAO.update(provider);

            return provider.asDTO();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    @Override
    public ProviderDTO update(ProviderDTO requestBody) {
        // map user DTO to entity
        Provider asProviderDBO = mapper.map(requestBody, Provider.class); // new shape-info

        Provider providerInDB = providerDAO.getById(asProviderDBO.getId());

        asProviderDBO.setAgency(providerInDB.getAgency());

        providerDAO.update(asProviderDBO);

        return asProviderDBO.asDTO();
    }

    public List<ClientDTO> getProviderClients(Long id) {
        Provider asProviderDTO = providerDAO.getById(id);

        // map entity to DTO
        final List<ClientDTO> asClientsDTO = new ArrayList<>();

        for (Client client : asProviderDTO.getClients()) {
            asClientsDTO.add(client.asDTO());
        }

        return asClientsDTO;
    }

}
