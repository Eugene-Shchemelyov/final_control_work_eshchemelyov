package com.gmail.eugene.controlwork.service.converter.impl;

import com.gmail.eugene.controlwork.repository.model.User;
import com.gmail.eugene.controlwork.repository.model.enums.RoleEnum;
import com.gmail.eugene.controlwork.service.converter.BusinessCardConverter;
import com.gmail.eugene.controlwork.service.converter.UserConverter;
import com.gmail.eugene.controlwork.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {
    private final BusinessCardConverter businessCardConverter;

    @Autowired
    public UserConverterImpl(BusinessCardConverter businessCardConverter) {
        this.businessCardConverter = businessCardConverter;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(RoleEnum.valueOf(user.getRole()));
        userDTO.setBusinessCards(
                user.getBusinessCards().stream()
                        .map(businessCardConverter::toDTO)
                        .collect(Collectors.toList())
        );
        return userDTO;
    }
}
