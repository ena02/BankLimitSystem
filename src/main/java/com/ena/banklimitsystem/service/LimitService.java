package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.model.LimitEntity;
import com.ena.banklimitsystem.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;

    public LimitEntity getLimitById(Integer limitId) {
        return limitRepository.findById(limitId).get();
    }

    public void addLimit(LimitEntity limit) {

        List<LimitEntity> limits = limitRepository.findAllByUserIdAndExpenseCategoryId(limit.getUserId(), limit.getExpenseCategoryId());

        System.out.println(limits.size());
        if (!limits.isEmpty()) {
            LimitEntity lustLimit = findLastDate(limits);
            System.out.println(lustLimit);
            limit.setRemainingLimit(limit.getLimitSum().subtract(lustLimit.getLimitSum().subtract(lustLimit.getRemainingLimit())) );
            System.out.println(limit.getLimitSum().subtract(lustLimit.getLimitSum().subtract(lustLimit.getRemainingLimit())));
        } else {
            limit.setRemainingLimit(limit.getLimitSum());
        }

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

    private LimitEntity findLastDate(List<LimitEntity> limitList) {

        Optional<LimitEntity> maxLimit = limitList.stream().max((lim1, lim2) -> lim1.getLimitDatetime().compareTo(lim2.getLimitDatetime()));
        return maxLimit.get();
    }
}
