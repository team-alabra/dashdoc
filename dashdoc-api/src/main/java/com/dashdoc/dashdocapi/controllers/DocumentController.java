package com.dashdocnow.controllers;

import com.dashdocnow.DTO.DocumentDTO;
import com.dashdocnow.services.DocumentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getById(@PathVariable Long id) throws JsonProcessingException {
        DocumentDTO myDocument = documentService.getById(id);

        return new ResponseEntity<>(
                myDocument,
                HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<DocumentDTO> save(@RequestBody DocumentDTO documentDTO) throws IOException {
        DocumentDTO newDocument = documentService.save(documentDTO);

        return new ResponseEntity<>(
                newDocument,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<DocumentDTO> update(@RequestBody DocumentDTO requestBody) throws JsonProcessingException {
        DocumentDTO documentToUpdate = documentService.update(requestBody);

        return new ResponseEntity<>(
                documentToUpdate,
                HttpStatus.OK);
    }

}
