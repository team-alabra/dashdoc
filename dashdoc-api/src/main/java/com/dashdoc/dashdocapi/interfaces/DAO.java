package com.dashdocnow.interfaces;

public interface DAO<T> {
    T getById(Long id);
    void save(T t);
    void update(T t);
}
