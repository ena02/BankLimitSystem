package com.ena.banklimitsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Entity(name = "limit_table")
public class LimitEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "expense_category_id")
    private Integer expenseCategoryId;

    @Column(name = "limit_sum")
    private BigDecimal limitSum;

    @Column(name = "remaining_limit")
    private BigDecimal remainingLimit;

    @Column(name = "limit_dateTIME")
    private ZonedDateTime limitDatetime;
}
