package com.dashdocnow.server_tests;

import com.dashdocnow.DTO.AgencyDTO;
import com.dashdocnow.DTO.ProviderDTO;
import com.dashdocnow.controllers.AgencyController;
import com.dashdocnow.entities.Agency;
import com.dashdocnow.repository.AgencyDAO;
import com.dashdocnow.utils.TestData;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AgencyControllerTests {
    @MockBean
    private AgencyDAO agencyDao;
    @Autowired
    private AgencyController agencyController;
    private final Agency sampleAgency = TestData.getAgencyDBO();
    Long agencyId = 1L;

    @Test
    public void getById_Returns_AgencyDTO() {
        // Arrange
        when(agencyDao.getById(agencyId)).thenReturn(sampleAgency);

        // Act
        AgencyDTO res = agencyController.getById(agencyId).getBody();

        // Assert
        Assertions.assertNotNull(res);
    }
    @Test
    public void getAgencyProvidersById_Returns_AgencyProviders() {
        // Arrange
        when(agencyDao.getById(agencyId)).thenReturn(sampleAgency);
        sampleAgency.setProviders(TestData.getAgencyProvidersDBO());

        // Act
        List<ProviderDTO> res = agencyController.getAgencyProvidersById(agencyId).getBody();

        // Assert
        Assertions.assertNotNull(res);
    }
    @Test
    public void save_Returns_ANewAgency() {
        // Arrange
        Agency agency = TestData.getAgencyDBO();
        agency.setId(null); // set id to null

        doAnswer(i -> {
            // This mimics what Hibernate does when we save an entity
            Agency ref = i.getArgument(0, Agency.class);
            ref.setId(3L);
            return ref;
        }).when(agencyDao).save(any(Agency.class));

        // Act
        AgencyDTO res = agencyController.save(agency.asDTO()).getBody();

        // Assert
        Assertions.assertNotNull(res);
        Assertions.assertEquals(3L, res.getId());
    }
    @Test
    public void update_Returns_UpdatedAgency() {
        // Arrange
        Agency agency = TestData.getAgencyDBO();
        agency.setPhoneNumber("7772274545"); // set id to null

        doNothing().when(agencyDao).update(any(Agency.class));

        // Act
        AgencyDTO res = agencyController.update(agency.asDTO()).getBody();

        // Assert
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res.getPhoneNumber(), "7772274545");
    }
}
