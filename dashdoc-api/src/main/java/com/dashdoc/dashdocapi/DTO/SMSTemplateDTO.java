package com.dashdocnow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSTemplateDTO {
    private Long id;
    private String templateContent;
    private String templateType;

    public SMSTemplateDTO() {
    }

    public SMSTemplateDTO(String templateContent, String templateType) {
        this.templateContent = templateContent;
        this.templateType = templateType;
    }

    public SMSTemplateDTO(Long id, String templateContent, String templateType) {
        this.id = id;
        this.templateContent = templateContent;
        this.templateType = templateType;
    }
}
