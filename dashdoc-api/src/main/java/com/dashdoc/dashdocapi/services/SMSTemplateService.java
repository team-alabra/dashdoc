package com.dashdocnow.services;

import com.dashdocnow.DTO.SMSTemplateDTO;
import com.dashdocnow.entities.SMSTemplate;
import com.dashdocnow.utils.BadRequestException;
import org.dozer.DozerBeanMapper;
import org.stringtemplate.v4.*;
import com.dashdocnow.DTO.SMSRequestDTO;
import com.dashdocnow.repository.SMSTemplateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SMSTemplateService {
    @Autowired
    private SMSTemplateDAO smsTemplateDAO;
    @Autowired
    private DozerBeanMapper mapper;

    public String getMessageContent(SMSRequestDTO requestDetails) {
        if (requestDetails == null)
            throw new BadRequestException("Not a valid SMS Request");

        var template = smsTemplateDAO.getByType(requestDetails.getMessageType());
        var provider = requestDetails.getProviderFirstName();
        var client = requestDetails.getClientFirstName();
        var data = requestDetails.getMessageData();

        var smsMessage = new ST(template.getTemplateContent());

        smsMessage.add("provider", provider);
        smsMessage.add("client", client);
        smsMessage.add("date", data.getDate().toString());
        smsMessage.add("link", data.getLink());
        smsMessage.add("code", data.getPassCode());

        return smsMessage.render();
    }

    public SMSTemplateDTO save(SMSTemplateDTO smsTemplate) throws BadRequestException {
        if (smsTemplate == null)
            throw new BadRequestException("No template to save");

        var templateToSave = mapper.map(smsTemplate, SMSTemplate.class);

        smsTemplateDAO.save(templateToSave);

        return templateToSave.asDTO();
    }

    public SMSTemplateDTO update(SMSTemplateDTO smsTemplate) throws BadRequestException {
        if (smsTemplate == null)
            throw new BadRequestException("No template to update");

        var templateToUpdate = mapper.map(smsTemplate, SMSTemplate.class);

        smsTemplateDAO.update(templateToUpdate);

        return templateToUpdate.asDTO();
    }
}
