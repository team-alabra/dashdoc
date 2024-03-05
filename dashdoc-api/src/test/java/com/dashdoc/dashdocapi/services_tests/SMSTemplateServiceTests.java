package com.dashdocnow.services_tests;

import com.dashdocnow.DTO.SMSRequestDTO;
import com.dashdocnow.DTO.models.SMSFields;
import com.dashdocnow.entities.SMSTemplate;
import com.dashdocnow.interfaces.enums.MessageType;
import com.dashdocnow.repository.SMSTemplateDAO;
import com.dashdocnow.services.SMSTemplateService;
import com.dashdocnow.utils.BadRequestException;
import com.dashdocnow.utils.MessagingTestData;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SMSTemplateServiceTests {
    @Autowired
    private SMSTemplateService smsTemplateService;
    @MockBean
    private SMSTemplateDAO mockTemplateDAO;

    @Test
    public void getMessageContent_success_returnsTemplatedContent(){
        // Arrange
        when(mockTemplateDAO.getByType(any(MessageType.class))).thenReturn(MessagingTestData.getSMSTemplate());
        var dummyDate = new DateTime();
        var requestDTO = new SMSRequestDTO(
                "Alan",
                "Brandy",
                MessageType.APPOINTMENT,
                new SMSFields(
                        null,
                        null,
                        dummyDate
                )
        );

        // Act
        var result = smsTemplateService.getMessageContent(requestDTO);

        // Assert
        assertEquals(result,"Test template for Brandy and Alan" );
    }

    @Test
    public void getMessageContent_throwsError_NoValidContent(){
        // Arrange
        String expectedMessage = "Not a valid SMS Request";

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> smsTemplateService.getMessageContent(null));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void save_success() {
        // Arrange
        doNothing().when(mockTemplateDAO).save(any(SMSTemplate.class));

        // Act
        var result = smsTemplateService.save(MessagingTestData.getSMSTemplate().asDTO());

        // Assert
        assertNotNull(result);
    }

    @Test
    public void save_failure() {
        // Arrange
        String expectedMessage = "No template to save";

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> smsTemplateService.save(null));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void update_success() {
        // Arrange
        doNothing().when(mockTemplateDAO).update(any(SMSTemplate.class));

        // Act
        var result = smsTemplateService.update(MessagingTestData.getSMSTemplate().asDTO());

        // Assert
        assertNotNull(result);
    }

    @Test
    public void update_failure() {
        // Arrange
        String expectedMessage = "No template to update";

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> smsTemplateService.update(null));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
