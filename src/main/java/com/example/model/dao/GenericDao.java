package com.example.model.dao;

import com.example.model.dao.exception.NotUniqueInsertionException;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    boolean create (T entity) throws NotUniqueInsertionException;
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
