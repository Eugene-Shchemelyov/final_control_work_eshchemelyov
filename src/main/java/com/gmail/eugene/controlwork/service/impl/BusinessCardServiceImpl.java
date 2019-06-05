package com.gmail.eugene.controlwork.service.impl;

import com.gmail.eugene.controlwork.repository.BusinessCardRepository;
import com.gmail.eugene.controlwork.service.BusinessCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusinessCardServiceImpl implements BusinessCardService {
    private final BusinessCardRepository businessCardRepository;

    @Autowired
    public BusinessCardServiceImpl(BusinessCardRepository businessCardRepository) {
        this.businessCardRepository = businessCardRepository;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        businessCardRepository.deleteById(id);
    }
}
