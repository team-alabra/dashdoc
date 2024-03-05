package com.dashdocapi.services;

import com.dashdocapi.DTO.AppointmentDTO;
import com.dashdocapi.interfaces.DashDocService;
import com.dashdocapi.repository.AppointmentDAO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentService implements DashDocService<AppointmentDTO> {
    @Autowired
    private AppointmentDAO appointmentDAO;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public AppointmentDTO getById(Long id) {
        return null;
    }

    @Override
    public AppointmentDTO save(AppointmentDTO t) {
        return null;
    }

    public void delete(AppointmentDTO t) {

    }

    public AppointmentDTO update(AppointmentDTO t) {
    return null;
    }
}
