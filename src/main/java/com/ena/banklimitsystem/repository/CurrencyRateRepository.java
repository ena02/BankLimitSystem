package com.ena.banklimitsystem.repository;

import com.ena.banklimitsystem.model.CurrencyRateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRateEntity, Integer> {

    CurrencyRateEntity findByBaseCurrencyAndQuoteCurrency(String fromCurrency, String toCurrency);
}
