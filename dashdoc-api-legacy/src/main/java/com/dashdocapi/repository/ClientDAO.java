package com.dashdocapi.repository;

import com.dashdocapi.entities.Client;
import com.dashdocapi.interfaces.DAO;
import com.dashdocapi.utils.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAO implements DAO<Client> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Client getById(Long id) {
        Client client = null;
        try {
           client = em.find(Client.class, id);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return client;
    }
    public Client getByPhoneNumber(String phoneNumber) {
        return em.createQuery("from Client where phoneNumber = :phoneNum", Client.class).setParameter("phoneNum", phoneNumber).getSingleResult();
    }

    @Override
    public void save(Client client) {
        em.persist(client);
    }

    @Override
    public void update(Client client) {
        em.merge(client);
        em.flush();
    }
    public void delete(Long id) {
        try {
            Client client = em.find(Client.class, id);
            em.remove(client);
            System.out.println("Deleted student successfully!");
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
