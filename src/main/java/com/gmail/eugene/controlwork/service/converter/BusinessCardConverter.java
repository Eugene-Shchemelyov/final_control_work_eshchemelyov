package com.gmail.eugene.controlwork.service.converter;

import com.gmail.eugene.controlwork.repository.model.BusinessCard;
import com.gmail.eugene.controlwork.service.model.BusinessCardDTO;

public interface BusinessCardConverter {
    BusinessCard toEntity(BusinessCardDTO businessCardDTO);

    BusinessCardDTO toDTO(BusinessCard businessCard);
}
