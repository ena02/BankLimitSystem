package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.model.LimitEntity;
import com.ena.banklimitsystem.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;

    public LimitEntity getLimitById(Integer limitId) {
        return limitRepository.findById(limitId).get();
    }

    public void addLimit(LimitEntity limit) {
        limitRepository.save(limit);
    }

    public List<LimitEntity> getAllLimits() {
        return limitRepository.findAll();
    }
}
