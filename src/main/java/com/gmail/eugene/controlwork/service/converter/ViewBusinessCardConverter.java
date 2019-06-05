package com.gmail.eugene.controlwork.service.converter;

import com.gmail.eugene.controlwork.repository.model.BusinessCard;
import com.gmail.eugene.controlwork.service.model.ViewBusinessCardDTO;

public interface ViewBusinessCardConverter {
    ViewBusinessCardDTO toDTO(BusinessCard businessCard);
}
