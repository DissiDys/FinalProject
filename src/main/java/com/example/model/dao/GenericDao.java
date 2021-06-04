package com.example.model.dao;

import com.example.model.dao.exception.NotUniqueInsertionException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    boolean create(T entity) throws NotUniqueInsertionException;

    Optional<T> findById(int id);

    List<T> findAll();

    void update(T entity);

    void delete(int id);

    void close();
}
