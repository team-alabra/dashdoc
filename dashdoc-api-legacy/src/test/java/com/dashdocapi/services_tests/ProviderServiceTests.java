package com.dashdocapi.services_tests;

import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.DTO.ClientDTO;
import com.dashdocapi.entities.Provider;
import com.dashdocapi.repository.ProviderDAO;
import com.dashdocapi.services.ProviderService;
import com.dashdocapi.utils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//*** Testing reference *** //
//https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-assertions-with-junit-5-api/
@SpringBootTest
public class ProviderServiceTests {
    @MockBean
    private ProviderDAO providerDAO;
    @Autowired
    private ProviderService providerService;
    private Provider sampleProvider;
    Long providerId = 1L;

    @Test
    public void getById_Returns_SoleProviderDTO() {
        // Arrange
        sampleProvider = TestData.getProviderDBO();
        System.out.println(sampleProvider);
        when(providerDAO.getById(providerId)).thenReturn(sampleProvider);

        // Act
        ProviderDTO res = providerService.getById(providerId);

        // Assert
        Assertions.assertNotNull(res);
    }
    @Test
    public void getById_Returns_ProviderWithAgency() {
        // Arrange
        sampleProvider = TestData.getAgencyAdminDBO();

        when(providerDAO.getById(providerId)).thenReturn(sampleProvider);

        // Act
        ProviderDTO res = providerService.getById(providerId);

        // Assert
        Assertions.assertNotNull(res);
        Assertions.assertNotNull(res.getAgencyID());
    }

    @Test
    public void getProviderClients_Returns_ListOfClients() {
        // Arrange
        sampleProvider = TestData.getAgencyAdminDBO();
        sampleProvider.setClients(TestData.getListOfClients(3));
        when(providerDAO.getById(providerId)).thenReturn(sampleProvider);

        // Act
        List<ClientDTO> res = providerService.getProviderClients(providerId);

        // Assert
        Assertions.assertNotNull(res);
        Assertions.assertEquals(3, res.size());
    }

    @Test
    public void save_Returns_ANewProvider() {
        // Arrange
        Provider provider = TestData.getProviderDBO();
        provider.setId(null); // set id to null

        doAnswer(i -> {
            // This mimics what Hibernate does when we save an entity
            Provider ref = i.getArgument(0, Provider.class);
            ref.setId(12L);
            return ref;
        }).when(providerDAO).save(any(Provider.class));

        // Act
        ProviderDTO res = providerService.save(provider.asDTO());

        // Assert
        Assertions.assertNotNull(res);
        Assertions.assertEquals(12L, res.getId());
    }

    @Test
    public void update_Returns_UpdatedProvider() {
        // Arrange
        Provider provider = TestData.getProviderDBO();
        when(providerDAO.getById(anyLong())).thenReturn(provider);

        doNothing().when(providerDAO).update(any(Provider.class));

        // Act
        ProviderDTO res = providerService.update(provider.asDTO());

        // Assert
        Assertions.assertNotNull(res);
    }

}
