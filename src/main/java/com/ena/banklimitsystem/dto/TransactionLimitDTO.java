package com.ena.banklimitsystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class TransactionLimitDTO {
    private Integer accountFrom;
    private Integer accountTo;
    private String transactionCurrency;
    private BigDecimal transactionSum;
    private Integer transactionCategoryId;
    private ZonedDateTime transactionDatetime;
    private Boolean limitExceeded;
    private BigDecimal limitSum;
    private ZonedDateTime limitDatetime;
    private String limitCurrency;
}
