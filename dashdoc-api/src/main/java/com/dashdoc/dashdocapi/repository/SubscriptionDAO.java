package com.dashdocnow.repository;

import com.dashdocnow.entities.Subscription;
import com.dashdocnow.interfaces.DAO;
import com.dashdocnow.utils.BadRequestException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionDAO implements DAO<Subscription> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Subscription getById(Long id) throws HibernateException, BadRequestException {
        return em.createQuery("from Subscription s where s.id = :id", Subscription.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void save(Subscription subscriptionToSave) throws HibernateException {
        em.persist(subscriptionToSave);
    }

    @Override
    public void update(Subscription subscriptionToUpdate) throws HibernateException {
        em.merge(subscriptionToUpdate);
        em.flush();
    }
}
