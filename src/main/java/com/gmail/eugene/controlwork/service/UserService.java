package com.gmail.eugene.controlwork.service;

import com.gmail.eugene.controlwork.service.model.BusinessCardDTO;
import com.gmail.eugene.controlwork.service.model.UserDTO;
import com.gmail.eugene.controlwork.service.model.ViewBusinessCardDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByUsername(String email);

    BusinessCardDTO add(BusinessCardDTO businessCardDTO);

    List<ViewBusinessCardDTO> getBusinessCards(Long userId);
}
