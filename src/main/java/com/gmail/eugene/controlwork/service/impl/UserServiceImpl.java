package com.gmail.eugene.controlwork.service.impl;

import com.gmail.eugene.controlwork.repository.BusinessCardRepository;
import com.gmail.eugene.controlwork.repository.UserRepository;
import com.gmail.eugene.controlwork.repository.model.BusinessCard;
import com.gmail.eugene.controlwork.repository.model.User;
import com.gmail.eugene.controlwork.service.UserService;
import com.gmail.eugene.controlwork.service.converter.BusinessCardConverter;
import com.gmail.eugene.controlwork.service.converter.UserConverter;
import com.gmail.eugene.controlwork.service.converter.ViewBusinessCardConverter;
import com.gmail.eugene.controlwork.service.model.BusinessCardDTO;
import com.gmail.eugene.controlwork.service.model.UserDTO;
import com.gmail.eugene.controlwork.service.model.ViewBusinessCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BusinessCardConverter businessCardConverter;
    private final BusinessCardRepository businessCardRepository;
    private final ViewBusinessCardConverter viewBusinessCardConverter;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserConverter userConverter,
            BusinessCardConverter businessCardConverter,
            BusinessCardRepository businessCardRepository,
            ViewBusinessCardConverter viewBusinessCardConverter
    ) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.businessCardConverter = businessCardConverter;
        this.businessCardRepository = businessCardRepository;
        this.viewBusinessCardConverter = viewBusinessCardConverter;
    }

    @Override
    @Transactional
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.loadUserByUsername(username);
        return userConverter.toDTO(user);
    }

    @Override
    @Transactional
    public BusinessCardDTO add(BusinessCardDTO businessCardDTO) {
        BusinessCard businessCard = businessCardConverter.toEntity(businessCardDTO);
        User user = userRepository.loadUserByUsername(businessCardDTO.getUsername());
        businessCard.setUser(user);
        businessCardRepository.create(businessCard);
        return businessCardDTO;
    }

    @Override
    @Transactional
    public List<ViewBusinessCardDTO> getBusinessCards(Long userId) {
        User user = userRepository.getById(userId);
        return user.getBusinessCards().stream()
                .map(viewBusinessCardConverter::toDTO)
                .collect(Collectors.toList());
    }
}

