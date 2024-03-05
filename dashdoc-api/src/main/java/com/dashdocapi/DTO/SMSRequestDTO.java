package com.dashdocapi.DTO;

import com.dashdocapi.DTO.models.SMSFields;
import com.dashdocapi.interfaces.enums.MessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSRequestDTO {
    private String clientFirstName;
    private String clientLastName;
    private String providerFirstName;
    private String providerLastName;
    private AppointmentDTO appointment;
    private MessageType messageType;
    private SMSFields messageData;

    public SMSRequestDTO() {
    }

    public SMSRequestDTO(String clientFirstName, String clientLastName, String providerFirstName, String providerLastName, AppointmentDTO appointment, MessageType messageType, SMSFields messageData) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.providerFirstName = providerFirstName;
        this.providerLastName = providerLastName;
        this.appointment = appointment;
        this.messageType = messageType;
        this.messageData = messageData;
    }

    public SMSRequestDTO(String clientFirstName, String providerFirstName, MessageType messageType, SMSFields messageData) {
        this.clientFirstName = clientFirstName;
        this.providerFirstName = providerFirstName;
        this.messageType = messageType;
        this.messageData = messageData;
    }
}
