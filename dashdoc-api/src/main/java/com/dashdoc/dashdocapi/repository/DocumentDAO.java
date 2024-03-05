package com.dashdocnow.repository;

import com.dashdocnow.entities.Document;
import com.dashdocnow.entities.FormFields;
import com.dashdocnow.interfaces.DAO;
import com.dashdocnow.utils.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DocumentDAO implements DAO<Document> {
    @PersistenceContext
    private EntityManager em;
    @Override
    public Document getById(Long id) {
        Document document = null;
        try {
            document = em.find(Document.class, id);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return document;
    }

    @Override
    public void save(Document newDocument) {
        try {
            em.persist(newDocument);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public void update(Document document) {
        try {
            em.merge(document);
            em.flush();
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public String getDocFormFields (String docName) {
        try {
          var documentData = em.createQuery("from FormFields f where f.docName = :docName", FormFields.class)
                        .setParameter("docName", docName)
                        .getSingleResult();

           return documentData.getData();
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
