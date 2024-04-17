package com.ena.banklimitsystem.controller;

import com.ena.banklimitsystem.dto.TransactionLimitDTO;
import com.ena.banklimitsystem.model.TransactionEntity;
import com.ena.banklimitsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
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

    @PostMapping("/createTransaction")
    public TransactionEntity createTransaction(@RequestBody TransactionEntity transactionEntity) {
        return transactionService.createTransaction(transactionEntity);
    }

    @GetMapping("/transactionByUserId/{id}")
    public List<TransactionLimitDTO> getTransactionsByUserId(@PathVariable("id") Integer id) {
        return transactionService.getAllTransactionByUserId(id);
    }
}
