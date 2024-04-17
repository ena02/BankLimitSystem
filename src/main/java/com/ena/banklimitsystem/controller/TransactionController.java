package com.ena.banklimitsystem.controller;

import com.ena.banklimitsystem.dto.TransactionLimitDTO;
import com.ena.banklimitsystem.model.TransactionEntity;
import com.ena.banklimitsystem.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "API for Transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/allTransaction")
    public List<TransactionEntity> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactionById/{id}")
    public TransactionEntity getTransactionsById(@PathVariable("id") Integer id) {
        return transactionService.getTransactionById(id);
    }

    @Operation(summary = "Make transaction")
    @PostMapping("/createTransaction")
    public TransactionEntity createTransaction(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Transaction request body",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    examples = @ExampleObject(value = "{\"accountFrom\": 2, \"accountTo\": 1, \"currencyShortname\": \"KZT\", \"sum\": 5000, \"expenseCategoryId\": 1}")
            )) TransactionEntity transactionEntity) {
        return transactionService.createTransaction(transactionEntity);
    }

    @GetMapping("/exceededTransactionsByUserId/{id}")
    public List<TransactionLimitDTO> getTransactionsByUserId(@PathVariable("id") Integer id) {
        return transactionService.getAllTransactionByUserId(id);
    }
}
