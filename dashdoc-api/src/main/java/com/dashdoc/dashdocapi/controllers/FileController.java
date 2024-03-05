package com.dashdocnow.controllers;

import com.dashdocnow.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/documents")
public class FileController {

    @Autowired
    FileService fileService;


    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable("fileName") String fileName) {
        ByteArrayOutputStream downloadInputStream = fileService.downloadFile(fileName);

        return ResponseEntity.ok()
                .contentType(fileService.contentType(fileName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(downloadInputStream.toByteArray());
    }

    @PostMapping("/createFile")
    public ResponseEntity<Object> save(@RequestParam("file") MultipartFile multipartFile) {
        fileService.uploadFile(multipartFile);
        return new ResponseEntity<>("Uploaded Successfully!", HttpStatus.OK);
    }


}
