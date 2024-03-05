package com.dashdocnow.server_tests;

import com.dashdocnow.DTO.models.FetchPlanRequest;
import com.dashdocnow.services.vendors.StripePlanServiceImpl;
import com.dashdocnow.utils.TestData;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StripePlanServiceImpl planService;
    private final String mockProductId;

    public PlanControllerTests(){
        this.mockProductId = "prod_12345";
    }
    @Test
    public void getById_Success_Returns_StripeProduct() throws Exception {
        // Arrange
        when(planService.getById(mockProductId)).thenReturn(new Product());

        // Act & Assert
        this.mockMvc.perform(get("/api/plan/{id}", mockProductId ))
                .andExpect(status().isOk());
    }

    @Test
    public void getById_Fail_ThrowsException() throws Exception {
        // Arrange
        when(planService.getById(any(String.class)))
            .thenThrow(new StripeException("Error from Stripe API", "111", "222", 400) {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            });

        // Act & Assert
        this.mockMvc.perform(get("/api/plan/{id}", mockProductId ))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllPlans_Success_Returns_StripeProducts() throws Exception {
        // Arrange
        when(planService.getAllPlans()).thenReturn(new ProductCollection());

        // Act & Assert
        this.mockMvc.perform(get("/api/plan/all-plans"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllPlans_Fail_ThrowsException() throws Exception {
        // Arrange
        when(planService.getAllPlans())
                .thenThrow(new StripeException("Error from Stripe API", "111", "222", 400) {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                });

        // Act & Assert
        this.mockMvc.perform(get("/api/plan/all-plans"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getByType_Success_Returns_PlanByQuery() throws Exception {
        // Arrange
        when(planService.getByType(any(FetchPlanRequest.class))).thenReturn(new Product());

        // Act & Assert
        this.mockMvc.perform(post("/api/plan/")
                .content(TestData.asJsonString(new FetchPlanRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getByType_Fail_ThrowsException() throws Exception {
        // Arrange
        when(planService.getByType(any(FetchPlanRequest.class)))
                .thenThrow(new StripeException("Error from Stripe API", "111", "222", 400) {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                });

        // Act & Assert
        this.mockMvc.perform(post("/api/plan/")
                .content(TestData.asJsonString(new FetchPlanRequest())))
                .andExpect(status().isBadRequest());
    }

}
