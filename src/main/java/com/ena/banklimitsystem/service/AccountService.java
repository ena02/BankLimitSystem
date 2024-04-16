package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.model.AccountEntity;
import com.ena.banklimitsystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountEntity createAccount(AccountEntity account) {
        return accountRepository.save(account);
    }

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountEntity getAccount(Integer accountId) {
        return accountRepository.findById(accountId).get();
    }
}
