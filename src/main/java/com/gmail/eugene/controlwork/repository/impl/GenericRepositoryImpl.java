package com.gmail.eugene.controlwork.repository.impl;

import com.gmail.eugene.controlwork.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public class GenericRepositoryImpl<I, T> implements GenericRepository<I, T> {
    protected Class<T> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericRepositoryImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T getById(I id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void deleteById(I id) {
        T entity = getById(id);
        delete(entity);
    }
}
