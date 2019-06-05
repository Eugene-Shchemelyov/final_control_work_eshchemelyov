package com.gmail.eugene.controlwork.repository.impl;

import com.gmail.eugene.controlwork.repository.UserRepository;
import com.gmail.eugene.controlwork.repository.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {
    @Override
    public User loadUserByUsername(String username) {
        String query = "FROM " + entityClass.getName() +
                " WHERE username =: username";
        Query createdQuery = entityManager.createQuery(query)
                .setParameter("username", username);
        return (User) createdQuery.getSingleResult();
    }
}
