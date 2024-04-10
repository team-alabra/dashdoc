package com.dashdocapi.controllers;

import com.dashdocapi.DTO.AppointmentDTO;
import com.dashdocapi.interfaces.AppController;
import com.dashdocapi.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
