package com.gmail.eugene.controlwork.repository.impl;

import com.gmail.eugene.controlwork.repository.BusinessCardRepository;
import com.gmail.eugene.controlwork.repository.model.BusinessCard;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessCardRepositoryImpl extends GenericRepositoryImpl<Long, BusinessCard> implements BusinessCardRepository {
}
