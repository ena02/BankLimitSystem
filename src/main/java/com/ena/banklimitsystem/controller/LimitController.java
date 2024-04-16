package com.ena.banklimitsystem.controller;

import com.ena.banklimitsystem.model.LimitEntity;
import com.ena.banklimitsystem.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/limit")
@RequiredArgsConstructor
public class LimitController {

    private final LimitService limitService;

    @GetMapping("/getLimitById/{id}")
    public LimitEntity getLimit(@PathVariable("id") Integer id) {
        return limitService.getLimitById(id);
    }

    @PostMapping("/add")
    public void addLimit(@RequestBody LimitEntity limitEntity) {
        limitService.addLimit(limitEntity);
    }

    @GetMapping("/allLimits")
    public List<LimitEntity> getAllLimits() {
        return limitService.getAllLimits();
    }
}
