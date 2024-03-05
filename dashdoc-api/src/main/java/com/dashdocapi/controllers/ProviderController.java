package com.dashdocapi.controllers;

import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.DTO.ClientDTO;
import com.dashdocapi.interfaces.AppController;
import com.dashdocapi.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ProviderController implements AppController<ProviderDTO> {
    @Autowired
    private ProviderService providerService;

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> getById(Long id) {
        ProviderDTO myUser = providerService.getById(id);

        return new ResponseEntity<>(
                myUser,
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProviderDTO> delete(@PathVariable Long id) {
        providerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping("/save")
    public ResponseEntity<ProviderDTO> save(ProviderDTO requestBody) {
        ProviderDTO newUser = providerService.save(requestBody);

        return new ResponseEntity<>(
                newUser,
                HttpStatus.OK);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<ProviderDTO> update(ProviderDTO requestBody) {
        ProviderDTO userToUpdate = providerService.update(requestBody);

        return new ResponseEntity<>(
                userToUpdate,
                HttpStatus.OK);
    }


    @GetMapping("/{id}/clients")
    public ResponseEntity<List<ClientDTO>> fetchProviderClients(@PathVariable Long id) {
        List<ClientDTO> providerClients = providerService.getProviderClients(id);

        return new ResponseEntity<>(
                providerClients,
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}/cancelSubscription")
    public ResponseEntity<ProviderDTO> cancelSubscription(@PathVariable Long id) {
       ProviderDTO provider = providerService.cancelProviderSubscription(id);
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

}
