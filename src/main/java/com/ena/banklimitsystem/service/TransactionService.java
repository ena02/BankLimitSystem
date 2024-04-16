package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.model.TransactionEntity;
import com.ena.banklimitsystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionEntity createTransaction(TransactionEntity transaction) {

        if (transaction.getDateTime() == null) transaction.setDateTime(ZonedDateTime.now());

        transaction.setLimitExceeded(false);
        return transactionRepository.save(transaction);
    }

    public TransactionEntity getTransactionById(Integer id) {
        return transactionRepository.findById(id).get();
    }

    public List<TransactionEntity> getAllTransactions() {
        return changeTimeZone(transactionRepository.findAll());
    }

    private List<TransactionEntity> changeTimeZone(List<TransactionEntity> transactions) {
        List<TransactionEntity> newTransactions = new ArrayList<>();

        for (TransactionEntity transaction : transactions) {
            transaction.setDateTime(transaction.getDateTime().withZoneSameInstant(ZoneId.systemDefault()));
            newTransactions.add(transaction);
        }

        return newTransactions;
    }
}
