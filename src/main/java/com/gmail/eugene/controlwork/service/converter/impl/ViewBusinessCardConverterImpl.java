package com.gmail.eugene.controlwork.service.converter.impl;

import com.gmail.eugene.controlwork.repository.model.BusinessCard;
import com.gmail.eugene.controlwork.service.converter.ViewBusinessCardConverter;
import com.gmail.eugene.controlwork.service.model.ViewBusinessCardDTO;
import org.springframework.stereotype.Component;

@Component
public class ViewBusinessCardConverterImpl implements ViewBusinessCardConverter {
    @Override
    public ViewBusinessCardDTO toDTO(BusinessCard businessCard) {
        ViewBusinessCardDTO viewBusinessCardDTO = new ViewBusinessCardDTO();
        viewBusinessCardDTO.setFullName(businessCard.getFullName());
        viewBusinessCardDTO.setPhone(businessCard.getPhone());
        viewBusinessCardDTO.setTitle(businessCard.getTitle());
        return viewBusinessCardDTO;
    }
}
