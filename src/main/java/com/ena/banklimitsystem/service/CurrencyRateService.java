package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.dto.CurrencyRateDto;
import com.ena.banklimitsystem.model.CurrencyRateEntity;
import com.ena.banklimitsystem.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final RestTemplate restTemplate;

    @Value("${cloud.twelvedata-apikey}")
    private String API_KEY;

    @Value("${cloud.api-url}")
    private String EXCHANGE_RATE_URL;


    public CurrencyRateEntity getCurrencyRate(String fromCurrency, String toCurrency) {

        CurrencyRateEntity currencyRateEntity = currencyRateRepository.findByBaseCurrencyAndQuoteCurrency(fromCurrency, toCurrency);

        if (currencyRateEntity == null) {
            CurrencyRateDto currencyRateDto = getCurrencyRateFromApi(fromCurrency, toCurrency);
            currencyRateEntity = new CurrencyRateEntity(fromCurrency, toCurrency, currencyRateDto.getExchangeRate(), currencyRateDto.getDateTime());
            currencyRateRepository.save(currencyRateEntity);
            return currencyRateEntity;
        } else if (ChronoUnit.DAYS.between(currencyRateEntity.getDatetime(), ZonedDateTime.now()) > 0) {
            CurrencyRateDto currencyRateDto = getCurrencyRateFromApi(fromCurrency, toCurrency);
            currencyRateEntity.setRate(currencyRateDto.getExchangeRate());
            currencyRateEntity.setDatetime(currencyRateDto.getDateTime());
            currencyRateRepository.save(currencyRateEntity);
            return currencyRateEntity;
        } else {
            return currencyRateEntity;
        }
    }

    public CurrencyRateDto getCurrencyRateFromApi(String fromCurrency, String toCurrency) {

        String apiUrl = EXCHANGE_RATE_URL + "?symbol=" + fromCurrency + "/" + toCurrency + "&apikey=" + API_KEY;

        ResponseEntity<CurrencyRateDto> responseEntity = restTemplate.getForEntity(apiUrl, CurrencyRateDto.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            CurrencyRateDto currencyRateDto = responseEntity.getBody();

            if (currencyRateDto != null && currencyRateDto.getExchangeRate() != null) {
                currencyRateDto.setBaseCurrency(fromCurrency);
                currencyRateDto.setQuoteCurrency(toCurrency);
                return currencyRateDto;
            }

        }
        return new CurrencyRateDto();
    }

    private ZonedDateTime convertEpochToZonedDateTime(long epochSeconds) {
        Instant instant = Instant.ofEpochSecond(epochSeconds);
        ZoneId userZoneId = ZoneId.systemDefault();
        return ZonedDateTime.ofInstant(instant, userZoneId);
    }

}
