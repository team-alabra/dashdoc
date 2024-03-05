package com.dashdocapi.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface DashDocService<T> {
    T getById(Long id) throws JsonProcessingException;
    T save(T t) throws IOException;
    T update(T t) throws JsonProcessingException;
}
