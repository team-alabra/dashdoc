package com.dashdocnow.services_tests;

import com.dashdocnow.services.vendors.NotificationsService;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NotificationsServiceTest {
    @Autowired
    private NotificationsService notificationsService;
    @MockBean
    private JavaMailSender mockSender;

    @Test
    public void sendNotificationEmail_Creates_EmailObject() throws Exception {
        // Arrange
        var mimeMessage = new MimeMessage((Session)null);
        when(mockSender.createMimeMessage()).thenReturn(mimeMessage);
        doNothing().when(mockSender).send(any(MimeMessage.class));

        // Act
        var res = notificationsService.sendNotificationEmail("tester@test.com", "TEST EMAIL", "This is a test");

        // Assert
        assertNotNull(res);
    }
}