package com.ena.banklimitsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CurrencyRateDto {

    private String baseCurrency;

    private String quoteCurrency;

    @JsonProperty("rate")
    private BigDecimal exchangeRate;

    @JsonProperty("timestamp")
    private ZonedDateTime dateTime;
}
