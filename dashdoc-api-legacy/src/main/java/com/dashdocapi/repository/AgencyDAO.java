package com.dashdocapi.repository;

import com.dashdocapi.entities.Agency;
import com.dashdocapi.interfaces.DAO;
import com.dashdocapi.utils.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AgencyDAO implements DAO<Agency> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Agency getById(Long id) {
        Agency agency = null;
        try {
            agency = em.find(Agency.class, id);
        } catch (Exception ex) {
            throw new NotFoundException(ex.getMessage());
        }

        return agency;
    }

    public Agency getByEmail(String email) {
        Agency agency = null;
        try {
            agency = em.createQuery("from Agency a where a.email = :email", Agency.class)
                    .setParameter("email", email).getSingleResult();
        } catch (Exception ex) {
            throw new NotFoundException(ex.getMessage());
        }

        return agency;
    }
    @Override
    public void save(Agency agency) {
        em.persist(agency);
    }

    @Override
    public void update(Agency agency) {
        em.merge(agency);
        em.flush();
    }

    public void delete(Long id) {
        try {
            Agency agency = em.find(Agency.class, id);
            em.remove(agency);
            System.out.println("deleted agency successfully");
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public void unsubscribe(Agency agency) {
        try {
            em.createQuery("update Agency a set a.subscriptionStatus = 'INACTIVE' where a.id = :id", Agency.class)
                    .setParameter("id", agency.getId())
                    .getSingleResult();
        } catch (Exception ex) {
            throw new NotFoundException(ex.getMessage());
        }
    }
}
