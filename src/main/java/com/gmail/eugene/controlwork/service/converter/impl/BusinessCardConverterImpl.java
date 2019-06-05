package com.gmail.eugene.controlwork.service.converter.impl;

import com.gmail.eugene.controlwork.repository.model.BusinessCard;
import com.gmail.eugene.controlwork.service.converter.BusinessCardConverter;
import com.gmail.eugene.controlwork.service.model.BusinessCardDTO;
import org.springframework.stereotype.Component;

@Component
public class BusinessCardConverterImpl implements BusinessCardConverter {
    @Override
    public BusinessCard toEntity(BusinessCardDTO businessCardDTO) {
        BusinessCard businessCard = new BusinessCard();
        businessCard.setFullName(businessCardDTO.getFullName());
        businessCard.setPhone(businessCardDTO.getPhone());
        businessCard.setTitle(businessCardDTO.getTitle());
        return businessCard;
    }

    @Override
    public BusinessCardDTO toDTO(BusinessCard businessCard) {
        BusinessCardDTO businessCardDTO = new BusinessCardDTO();
        businessCardDTO.setFullName(businessCard.getFullName());
        businessCardDTO.setPhone(businessCard.getPhone());
        businessCardDTO.setTitle(businessCard.getTitle());
        return businessCardDTO;
    }
}
