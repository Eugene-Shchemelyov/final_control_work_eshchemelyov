package com.gmail.eugene.controlwork.repository;

import com.gmail.eugene.controlwork.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User> {
    User loadUserByUsername(String username);
}
