package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.model.CurrencyRateEntity;
import com.ena.banklimitsystem.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;

    public void addCurrencyPair(CurrencyRateEntity currencyRateEntity) {
        currencyRateRepository.save(currencyRateEntity);
    }
}
