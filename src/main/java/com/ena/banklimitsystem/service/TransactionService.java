package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.model.TransactionEntity;
import com.ena.banklimitsystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionEntity createTransaction(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    public TransactionEntity getTransactionById(Integer id) {
        return transactionRepository.findById(id).get();
    }

    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
