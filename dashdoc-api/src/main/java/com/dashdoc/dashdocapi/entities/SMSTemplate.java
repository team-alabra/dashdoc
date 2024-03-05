package com.dashdocnow.entities;

import com.dashdocnow.DTO.SMSTemplateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "sms_template")
@Getter
@Setter
public class SMSTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private String templateContent;
    @Column(name = "type")
    private String templateType;
    @Column(name = "last_updated")
    private Date lastUpdated = new Date();
    @Column(name = "created_on")
    private Date createdOn;
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Date();
    }

    public SMSTemplate() {
    }

    public SMSTemplate(Long id, String templateContent, String templateType, Date lastUpdated) {
        this.id = id;
        this.templateContent = templateContent;
        this.templateType = templateType;
        this.lastUpdated = lastUpdated;
    }

    public SMSTemplate(Long id, String templateContent, String templateType) {
        this.id = id;
        this.templateContent = templateContent;
        this.templateType = templateType;
    }

    public SMSTemplateDTO asDTO(){
        return new SMSTemplateDTO(
            templateContent,
            templateType
        );
    }
}
