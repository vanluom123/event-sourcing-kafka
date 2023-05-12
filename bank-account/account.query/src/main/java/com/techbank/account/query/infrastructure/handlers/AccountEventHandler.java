package com.techbank.account.query.infrastructure.handlers;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundsDepositedEvent;
import com.techbank.account.common.events.FundsWithdrawnEvent;
import com.techbank.account.query.common.exception.ResourceNotFoundException;
import com.techbank.account.query.domain.AccountRepository;
import com.techbank.account.query.domain.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountEventHandler implements EventHandler {
    private final AccountRepository accountRepository;

    public AccountEventHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var bankAccount = accountRepository.findById(event.getId())
                .orElseThrow(() -> {
                    log.info("Cannot found bank account");
                    return new ResourceNotFoundException("Cannot found bank account");
                });
        var currentBalance = bankAccount.getBalance();
        var latestBalance = currentBalance + event.getAmount();
        bankAccount.setBalance(latestBalance);
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        var bankAccount = accountRepository.findById(event.getId())
                .orElseThrow(() -> {
                    log.info("Cannot found bank account");
                    return new ResourceNotFoundException("Cannot found bank account");
                });
        var currentBalance = bankAccount.getBalance();
        var latestBalance = currentBalance - event.getAmount();
        bankAccount.setBalance(latestBalance);
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
