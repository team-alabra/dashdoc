package com.dashdocnow.repository;

import com.dashdocnow.entities.Provider;
import com.dashdocnow.interfaces.DAO;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Repository
public class ProviderDAO implements DAO<Provider> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Provider getById(Long id) {
        return em.find(Provider.class, id);
    }

    public Provider getByEmail(String email) {
        return  em.createQuery("from Provider p where p.email = :email", Provider.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Provider getAgencyAdmin(Long agencyId) {
        return em.createQuery("from Provider p where p.agency.id = :id", Provider.class)
                .setParameter("id", agencyId)
                .getSingleResult();
    }

    @Override
    public void save(Provider provider) {
            em.persist(provider);
    }

    @Override
    public void update(Provider provider) {
        em.merge(provider);
        em.flush();
    }

    public void delete(Long id) {
        Provider provider = em.find(Provider.class, id);
        em.remove(provider);
    }

}
