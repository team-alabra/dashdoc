package com.dashdocapi.repository;

import com.dashdocapi.entities.Appointment;
import com.dashdocapi.interfaces.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentDAO implements DAO<Appointment> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Appointment getById(Long id) {
        return null;
    }

    @Override
    public void save(Appointment appointment) {
    }

    @Override
    public void update(Appointment appointment) {
    }

    public void delete(Appointment appointment) {
    }
}
