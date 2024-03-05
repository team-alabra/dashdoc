package com.dashdocnow.interfaces;

import com.dashdocnow.utils.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface DashDocService<T> {
    T getById(Long id) throws JsonProcessingException;
    T save(T t) throws IOException;
    T update(T t) throws JsonProcessingException;
}
