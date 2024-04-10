package com.dashdocapi.server_tests;


import com.dashdocapi.DTO.DocumentDTO;
import com.dashdocapi.controllers.DocumentController;
import com.dashdocapi.entities.Document;
import com.dashdocapi.entities.Provider;
import com.dashdocapi.entities.Client;
import com.dashdocapi.repository.ClientDAO;
import com.dashdocapi.repository.DocumentDAO;
import com.dashdocapi.repository.ProviderDAO;
import com.dashdocapi.utils.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DocumentControllerTests {

    @MockBean
    private DocumentDAO documentDAO;
    @MockBean
    private ClientDAO clientDAO;
    @MockBean
    private ProviderDAO providerDAO;
    @Autowired
    private DocumentController documentController;
    private Document sampleDocument;
    Long documentId = 1L;

    @BeforeEach
    public void init(){
        sampleDocument = TestData.getDocumentDBO();
        when(documentDAO.getById(anyLong())).thenReturn(sampleDocument);
    }

    @Test
    public void getById_Returns_DocumentDTO () throws JsonProcessingException {
        // Act
        DocumentDTO result = documentController.getById(documentId).getBody();
        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void save_Returns_ANewDocument () throws IOException {
        Document document = TestData.getDocumentDBO();
        Client sampleClient = TestData.getClientDBO();
        Provider sampleProvider = TestData.getProviderDBO();
        
        when(providerDAO.getById(anyLong())).thenReturn(sampleProvider);
        when(clientDAO.getById(anyLong())).thenReturn(sampleClient);
        when(clientDAO.getById(anyLong())).thenReturn(sampleClient);
        when(documentDAO.getDocFormFields(anyString())).thenReturn(sampleDocument.getFormFields());

        document.setId(null);

        doAnswer(i -> {
            Document ref = i.getArgument(0, Document.class);
            ref.setId(3L);
            return ref;
        }).when(documentDAO).save(any(Document.class));

        // Act
        DocumentDTO result = documentController.save(document.asDTO()).getBody();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3L, result.getId());
    }

    @Test
    public void update_Returns_UpdatedDocument() throws JsonProcessingException {
        // Arrange
        doNothing().when(documentDAO).update(any(Document.class));

        //Act
        DocumentDTO result = documentController.update(sampleDocument.asDTO()).getBody();

        Assertions.assertNotNull(result);
    }
}
