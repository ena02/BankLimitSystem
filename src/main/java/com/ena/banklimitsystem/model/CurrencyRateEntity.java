package com.ena.banklimitsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Entity(name = "currency_rate")
@NoArgsConstructor
public class CurrencyRateEntity {

    @Id
    @Column(name = "pair_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "quote_currency")
    private String quoteCurrency;

    @Column(name = "exchange_rate")
    private BigDecimal rate;

    @Column(name = "datetime")
    private ZonedDateTime datetime;

    public CurrencyRateEntity(String fromCurrency, String toCurrency, BigDecimal exchangeRate, ZonedDateTime time) {
        this.baseCurrency = fromCurrency;
        this.quoteCurrency = toCurrency;
        this.rate = exchangeRate;
        this.datetime = time;
    }
}
