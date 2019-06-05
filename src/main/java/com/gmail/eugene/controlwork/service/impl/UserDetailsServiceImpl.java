package com.gmail.eugene.controlwork.service.impl;

import com.gmail.eugene.controlwork.service.UserService;
import com.gmail.eugene.controlwork.service.model.AppUserPrincipal;
import com.gmail.eugene.controlwork.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException(String.format("%s: %s, %s.", "User with username", username, "not found."));
        }
        return new AppUserPrincipal(userDTO);
    }
}
