package com.dashdocnow.repository;

import com.dashdocnow.entities.SMSTemplate;
import com.dashdocnow.interfaces.DAO;
import com.dashdocnow.interfaces.enums.MessageType;
import com.dashdocnow.utils.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SMSTemplateDAO implements DAO<SMSTemplate> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public SMSTemplate getById(Long id) {
        SMSTemplate template = null;
        try {
            template = em.find(SMSTemplate.class, id);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }

        return template;
    }
    public SMSTemplate getByType(MessageType messageType) {
        SMSTemplate template = null;
        try {
            template = em.createQuery("from SMSTemplate t where t.templateType = :messageType", SMSTemplate.class)
                    .setParameter("messageType", messageType.name())
                    .getSingleResult();
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }

        return template;
    }

    @Override
    public void save(SMSTemplate template) { em.persist(template); }

    @Override
    public void update(SMSTemplate template) {
        try {
            em.merge(template);
            em.flush();
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
