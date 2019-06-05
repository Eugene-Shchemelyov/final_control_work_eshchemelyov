package com.gmail.eugene.controlwork.repository;

public interface GenericRepository<I, T> {
    void create(T entity);

    void delete(T entity);

    T getById(I id);

    void deleteById(I id);
}
