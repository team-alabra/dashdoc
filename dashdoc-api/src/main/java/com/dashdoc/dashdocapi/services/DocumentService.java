package com.dashdocnow.services;

import com.dashdocnow.DTO.DocumentDTO;
import com.dashdocnow.entities.Document;
import com.dashdocnow.entities.Provider;
import com.dashdocnow.entities.Client;
import com.dashdocnow.interfaces.DashDocService;
import com.dashdocnow.repository.DocumentDAO;
import com.dashdocnow.repository.ProviderDAO;
import com.dashdocnow.repository.ClientDAO;

import com.dashdocnow.utils.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;

@Service
@Transactional
public class DocumentService implements DashDocService<DocumentDTO> {
    @Autowired
    private DocumentDAO documentDAO;
    @Autowired
    private ProviderDAO providerDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public DocumentDTO getById(Long id) throws JsonProcessingException {
        Document document = null;
        try {
            if (id == null) {
                throw new BadRequestException("No document with that id exists");
            }
            document = documentDAO.getById(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        return document.asDTO();
    }
    @Override
    public DocumentDTO save(DocumentDTO documentDTO) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            Document asDocumentDBO = mapper.map(documentDTO, Document.class);

            String docName = documentDTO.getDocumentType() + "_" + documentDTO.getDocumentName() + "_" + documentDTO.getAgeGroup();

            // Java Object to JSON string
            String jsonString = objectMapper.writeValueAsString(documentDTO.getFormFields());

            asDocumentDBO.setFormFields(jsonString);

            if (documentDTO.getProviderID() != null) {
                Provider provider = providerDAO.getById(documentDTO.getProviderID());
                asDocumentDBO.setProvider(provider);
            }

            if (documentDTO.getClientID() != null) {
                Client client = clientDAO.getById(documentDTO.getClientID());
                asDocumentDBO.setClient(client);
            }

            // get form fields based on document type, name, age group
            var fields = documentDAO.getDocFormFields(docName);
            asDocumentDBO.setFormFields(fields);

            // save newly created document type
            documentDAO.save(asDocumentDBO);

            return asDocumentDBO.asDTO();
    }

    @Override
    public DocumentDTO update(DocumentDTO requestBody) throws JsonProcessingException {

        // map user DTO to entity
        Document asDocumentDBO = mapper.map(requestBody, Document.class); // new shape-info
        Document documentInDB = documentDAO.getById(asDocumentDBO.getId());

        asDocumentDBO.setProvider(documentInDB.getProvider());
        asDocumentDBO.setClient(documentInDB.getClient());

        documentDAO.update(asDocumentDBO);

        return asDocumentDBO.asDTO();
    }
}
