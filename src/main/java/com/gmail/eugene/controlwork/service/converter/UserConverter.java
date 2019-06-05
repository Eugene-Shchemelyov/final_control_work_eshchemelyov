package com.gmail.eugene.controlwork.service.converter;

import com.gmail.eugene.controlwork.repository.model.User;
import com.gmail.eugene.controlwork.service.model.UserDTO;

public interface UserConverter {
    UserDTO toDTO(User user);
}
