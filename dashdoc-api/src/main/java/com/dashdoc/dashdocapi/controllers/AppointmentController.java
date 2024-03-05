package com.dashdocnow.controllers;

import com.dashdocnow.DTO.AppointmentDTO;
import com.dashdocnow.interfaces.AppController;
import com.dashdocnow.services.AppointmentService;
import com.dashdocnow.services.vendors.NotificationsService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/appointment")
public class AppointmentController implements AppController<AppointmentDTO> {
    @Autowired
    private AppointmentService appointmentService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(Long id) {
        AppointmentDTO myAppt = appointmentService.getById(id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentDTO> delete(Long id) {
        return null;
    }

    @Override
    @PostMapping("/save")
    public ResponseEntity<AppointmentDTO> save(AppointmentDTO requestBody) { return null; }

    @Override
    public ResponseEntity<AppointmentDTO> update(AppointmentDTO requestBody) {
        return null;
    }

}
