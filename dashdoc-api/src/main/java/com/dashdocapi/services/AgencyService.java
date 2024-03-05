package com.dashdocapi.services;

import com.dashdocapi.DTO.AgencyDTO;
import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.DTO.ClientDTO;
import com.dashdocapi.entities.Agency;
import com.dashdocapi.entities.Provider;
import com.dashdocapi.entities.Client;
import com.dashdocapi.interfaces.DashDocService;
import com.dashdocapi.repository.AgencyDAO;
import com.dashdocapi.utils.BadRequestException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AgencyService implements DashDocService<AgencyDTO> {
    @Autowired
    private AgencyDAO agencyDAO;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public AgencyDTO getById(Long id) {
        Agency myAgency = agencyDAO.getById(id);
        if (myAgency == null) {
            throw new BadRequestException("No Agency with that id exists");
        }
        return myAgency.asDTO();
    }

    @Override
    public AgencyDTO save(AgencyDTO agencyDTO) {
        Agency asAgencyDBO = mapper.map(agencyDTO, Agency.class);

        agencyDAO.save(asAgencyDBO);

        return asAgencyDBO.asDTO();
    }

    @Override
    public AgencyDTO update(AgencyDTO agencyDTO) {
        Agency asAgencyDBO = mapper.map(agencyDTO, Agency.class);

        agencyDAO.update(asAgencyDBO);

        return asAgencyDBO.asDTO();
    }

    public List<ProviderDTO> getAgencyProvidersById(Long id) {
        Agency agencyDBO = agencyDAO.getById(id);

        if (agencyDBO == null)
            throw new BadRequestException("No Agency found with that ID");

        // convert an array of Provider.class to an array of ProviderDTO.class
        final List<ProviderDTO> providersAsDTO = new ArrayList<>();

        for (Provider element : agencyDBO.getProviders()) {
            providersAsDTO.add(element.asDTO());
        }

        return providersAsDTO;
    }

    public List<ClientDTO> getAgencyClientsById(Long id) {
        Agency agencyDBO = agencyDAO.getById(id);

        // convert an array of Provider.class to an array of ProviderDTO.class
        final List<ClientDTO> studentsAsDTO = new ArrayList<>();

        for (Client client : agencyDBO.getClients()) {
            studentsAsDTO.add(client.asDTO());
        }

        return studentsAsDTO;
    }
}
