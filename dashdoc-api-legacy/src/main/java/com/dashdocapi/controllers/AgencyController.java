package com.dashdocapi.controllers;

import com.dashdocapi.DTO.AgencyDTO;
import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.DTO.ClientDTO;
import com.dashdocapi.interfaces.AppController;
import com.dashdocapi.services.AgencyService;
import com.dashdocapi.utils.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/agency")
public class AgencyController implements AppController<AgencyDTO> {
    @Autowired
    private AgencyService agencyService;

    @Override
    @PostMapping("/save")
    public ResponseEntity<AgencyDTO> save(@Valid AgencyDTO agencyDTO) {
        AgencyDTO newAgency = agencyService.save(agencyDTO);

        return new ResponseEntity<>(
                newAgency,
                HttpStatus.OK);
    }
    @Override
    @PutMapping("/update")
    public ResponseEntity<AgencyDTO> update(@Valid AgencyDTO requestBody) {
        AgencyDTO updatedAgency = agencyService.update(requestBody);

        return new ResponseEntity<>(
                updatedAgency,
                HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AgencyDTO> getById(Long id) throws BadRequestException {
        AgencyDTO res = agencyService.getById(id);

        return new ResponseEntity<>(
                res,
                HttpStatus.OK);
    }

    @GetMapping("/{id}/providers")
    public ResponseEntity<List<ProviderDTO>> getAgencyProvidersById(@PathVariable Long id) throws BadRequestException {

        List<ProviderDTO> agencyProviders = agencyService.getAgencyProvidersById(id);

        return new ResponseEntity<>(
                agencyProviders,
                HttpStatus.OK);
    }

    @GetMapping("/{id}/clients")
    public ResponseEntity<List<ClientDTO>> getAgencyClientsById(@PathVariable Long id) throws BadRequestException {

        List<ClientDTO> agencyStudents = agencyService.getAgencyClientsById(id);

        return new ResponseEntity<>(
                agencyStudents,
                HttpStatus.OK);
    }
}
