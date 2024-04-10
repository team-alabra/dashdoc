package com.dashdocapi;

import com.dashdocapi.services.ProviderService;
import com.dashdocapi.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigurationTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProviderService mockProviderService;

    @BeforeEach
    public void setup(){
        var testProvider = TestData.getProviderDBO().asDTO();
        when(mockProviderService.getById(anyLong())).thenReturn(testProvider);
        doNothing().when(mockProviderService).delete(anyLong());
    }

    @WithMockUser(authorities = {"SOLE_PROVIDER"})
    @Test
    public void calls_GetUser_GetsOKResponse() throws Exception {
        // Act & Assert
        this.mockMvc.perform(get("/api/user/{userId}", 1L )).andExpect(status().isOk());
    }

    @WithMockUser(authorities = {"NOT_A_REAL_ROLE"})
    @Test
    public void calls_GetUser_GetsForbiddenResponse() throws Exception {
        // Act & Assert
        this.mockMvc.perform(get("/api/user/{userId}", 1L )).andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"AGENCY_PROVIDER"})
    @Test
    public void calls_DeleteUser_GetsForbiddenResponse() throws Exception {
        // Act & Assert
        this.mockMvc.perform(delete("/api/user/{userId}", 1L )).andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ADMIN"})
    @Test
    public void calls_DeleteUser_GetsOKResponse() throws Exception {
        // Act & Assert
        this.mockMvc.perform(delete("/api/user/{userId}", 1L )).andExpect(status().isOk());
    }
}
