package com.dashdocapi.interfaces;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AppController<T> {
    ResponseEntity<T> getById(@PathVariable Long id);
    ResponseEntity<T> save(@RequestBody T requestBody) throws MessagingException;
    ResponseEntity<T> update(@RequestBody T requestBody);
}
