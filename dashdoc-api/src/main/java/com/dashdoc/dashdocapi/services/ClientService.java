package com.dashdocnow.services;

import com.dashdocnow.DTO.ClientDTO;
import com.dashdocnow.entities.Client;
import com.dashdocnow.interfaces.DashDocService;
import com.dashdocnow.repository.AgencyDAO;
import com.dashdocnow.repository.ProviderDAO;
import com.dashdocnow.repository.ClientDAO;
import com.dashdocnow.utils.BadRequestException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService implements DashDocService<ClientDTO> {
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private ProviderDAO providerDAO;
    @Autowired
    private AgencyDAO agencyDAO;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public ClientDTO getById(Long id) {
        Client myClient = clientDAO.getById(id);
        if (myClient == null) {
            throw new BadRequestException("No Student with that id exists");
        }
        return myClient.asDTO();
    }

    @Override
    public ClientDTO save(ClientDTO requestBody) {
        // map dto to entity
        Client asClientDBO = mapper.map(requestBody, Client.class);
        Long providerId = requestBody.getProviderID();
        Long agencyId = requestBody.getAgencyID();

        if (providerId != null) {
            asClientDBO.setProvider(providerDAO.getById(providerId));
        }

        if (agencyId != null) {
            asClientDBO.setAgency(agencyDAO.getById(agencyId));
        }

        clientDAO.save(asClientDBO);

        // mapped entity to the DTO
        return asClientDBO.asDTO();
    }

    @Override
    public ClientDTO update(ClientDTO requestBody) {
        Client asClientDBO = mapper.map(requestBody, Client.class);

        if (requestBody.getProviderID() != null) {
            asClientDBO.setProvider(providerDAO.getById(requestBody.getProviderID()));
        }

        clientDAO.update(asClientDBO);

        return asClientDBO.asDTO();
    }

    // TODO - this may require detaching the provider and/or agency
    public void delete(Long id) {
        clientDAO.delete(id);
    }

}
