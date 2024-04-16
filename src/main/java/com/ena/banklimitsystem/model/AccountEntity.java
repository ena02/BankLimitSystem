package com.ena.banklimitsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "account")
public class AccountEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "balance")
    private BigDecimal balance;
}
