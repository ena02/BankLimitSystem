package com.ena.banklimitsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Entity(name = "transaction_table")
public class TransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_from")
    private Integer accountFrom;

    @Column(name = "account_to")
    private Integer accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "expense_category_id")
    private Integer expenseCategoryId;

    @Column(name = "datetime")
    private ZonedDateTime dateTime;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    @Column(name = "exceeded_limit_id")
    private Integer exceededLimitId;
}
