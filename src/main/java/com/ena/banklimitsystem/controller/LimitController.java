package com.ena.banklimitsystem.controller;

import com.ena.banklimitsystem.model.LimitEntity;
import com.ena.banklimitsystem.service.LimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/limit")
@RequiredArgsConstructor
@Tag(name = "Limit")
public class LimitController {

    private final LimitService limitService;

    @GetMapping("/getLimitById/{id}")
    public LimitEntity getLimit(@PathVariable("id") Integer id) {
        return limitService.getLimitById(id);
    }

    @Operation(summary = "Add limit")
    @PostMapping("/addLimit")
    public LimitEntity addLimit(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Limit request body",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    examples = @ExampleObject(value = "{\"userId\": 2, \"expenseCategoryId\": 2, \"limitSum\": 2000}")
            )) LimitEntity limitEntity) {
        return limitService.addLimit(limitEntity);
    }

    @GetMapping("/allLimits")
    public List<LimitEntity> getAllLimits() {
        return limitService.getAllLimits();
    }

    @GetMapping("/lastLimit")
    public LimitEntity getLastLimit() {
        return limitService.lastLimit();
    }
}
