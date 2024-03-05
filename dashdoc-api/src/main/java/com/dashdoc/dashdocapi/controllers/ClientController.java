package com.dashdocnow.controllers;

import com.dashdocnow.DTO.ClientDTO;
import com.dashdocnow.interfaces.AppController;
import com.dashdocnow.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/client")
public class ClientController implements AppController<ClientDTO> {
    @Autowired
    private ClientService clientService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getById(Long id) {
        ClientDTO myStudent = clientService.getById(id);
        return new ResponseEntity<>(myStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping("/save")
    public ResponseEntity<ClientDTO> save(ClientDTO requestBody) {
        ClientDTO newStudent = clientService.save(requestBody);
        return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<ClientDTO> update(ClientDTO requestBody) {
        ClientDTO clientToUpdate = clientService.update(requestBody);
        return new ResponseEntity<>(clientToUpdate, HttpStatus.OK);
    }


}
