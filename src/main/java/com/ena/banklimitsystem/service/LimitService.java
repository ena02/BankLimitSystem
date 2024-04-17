package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.model.LimitEntity;
import com.ena.banklimitsystem.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;
    private final CurrencyRateService currencyRateService;

    public LimitEntity getLimitById(Integer limitId) {
        return limitRepository.findById(limitId).get();
    }

    public void addLimit(LimitEntity limit) {

        List<LimitEntity> limits = limitRepository.findAllByUserIdAndExpenseCategoryId(limit.getUserId(), limit.getExpenseCategoryId());

        System.out.println(limits.size());
        if (!limits.isEmpty()) {
            LimitEntity lustLimit = findLastDate(limits);
            limit.setRemainingLimit(limit.getLimitSum().subtract(lustLimit.getLimitSum().subtract(lustLimit.getRemainingLimit())) );
        } else {
            limit.setRemainingLimit(limit.getLimitSum());
        }

        limit.setCurrencyShortname("USD");
        limit.setLimitDatetime(ZonedDateTime.now());
        limitRepository.save(limit);
    }

    public List<LimitEntity> getAllLimits() {
        return limitRepository.findAll();
    }

    public LimitEntity lastLimit() {
        return findLastDate(limitRepository.findAll());
    }

    public List<LimitEntity> findAllByUserIdAndExpenseId(Integer userId, Integer expenseId) {
        return limitRepository.findAllByUserIdAndExpenseCategoryId(userId, expenseId);
    }

    public Integer isExceeded(Integer userId, Integer expenseId, BigDecimal transactionSum, String currency) {

        LimitEntity limitEntity;

        try {
            limitEntity = findLastDate(limitRepository.findAllByUserIdAndExpenseCategoryId(userId, expenseId));
        } catch (NoSuchElementException e) {
            LimitEntity newLimit = new LimitEntity();
            newLimit.setUserId(userId);
            newLimit.setExpenseCategoryId(expenseId);
            newLimit.setLimitSum(BigDecimal.valueOf(1000));
            addLimit(newLimit);
            limitEntity = findLastDate(limitRepository.findAllByUserIdAndExpenseCategoryId(userId, expenseId));
        }

        if (!currency.equals("USD")) {
            BigDecimal rate = currencyRateService.getCurrencyRate("USD", currency).getRate();
            transactionSum = transactionSum.divide(rate, 5 , BigDecimal.ROUND_HALF_UP);
        }


        if (limitEntity.getRemainingLimit().compareTo(transactionSum) < 0) {
            limitEntity.setRemainingLimit(limitEntity.getRemainingLimit().subtract(transactionSum));
            limitRepository.save(limitEntity);
            return limitEntity.getId();
        } else {
            limitEntity.setRemainingLimit(limitEntity.getRemainingLimit().subtract(transactionSum));
            limitRepository.save(limitEntity);
            return -1;
        }
    }

    private LimitEntity findLastDate(List<LimitEntity> limitList) {

        Optional<LimitEntity> maxLimit = limitList.stream().max((lim1, lim2) -> lim1.getLimitDatetime().compareTo(lim2.getLimitDatetime()));
        return maxLimit.get();
    }
}
