package com.ena.banklimitsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity(name = "currency_rate")
public class CurrencyRateEntity {

    @Id
    @Column(name = "pairs_id")
    private Integer id;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "quote_currency")
    private String quoteCurrency;

    @Column(name = "exchange_rate")
    private BigDecimal rate;

    @Column(name = "datetime")
    private Timestamp datetime;
}
