package com.techbank.account.query.api.queries;

import com.techbank.account.query.api.dto.EqualityType;
import com.techbank.account.query.domain.AccountRepository;
import com.techbank.account.query.domain.BankAccount;
import com.techbank.cqrs.core.domain.BaseEntity;
import com.techbank.cqrs.core.queries.BaseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler {
    private final AccountRepository accountRepository;

    public AccountQueryHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<BaseEntity> handleFindAllAccount(BaseQuery query) {
        Iterable<BankAccount> bankAccounts = accountRepository.findAll();
        List<BaseEntity> bankAccountsList = new ArrayList<>();
        bankAccounts.forEach(bankAccountsList::add);
        return bankAccountsList;
    }

    @Override
    public List<BaseEntity> handleFindAccountById(BaseQuery query) {
        FindAccountByIdQuery findAccountByIdQuery = (FindAccountByIdQuery)query;
        var bankAccount = accountRepository.findById(findAccountByIdQuery.getId());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount.get());
        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handleFindAccountByHolder(BaseQuery query) {
        FindAccountByHolderQuery findAccountByHolderQuery = (FindAccountByHolderQuery)query;
        var bankAccount = accountRepository.findByAccountHolder(findAccountByHolderQuery.getAccountHolder());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount.get());
        return bankAccountList;
    }

    @Override
    public List<BaseEntity> handleFindAccountWithBalance(BaseQuery query) {
        FindAccountWithBalanceQuery findAccountWithBalanceQuery = (FindAccountWithBalanceQuery)query;
        List<BaseEntity> bankAccountsList = findAccountWithBalanceQuery.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(findAccountWithBalanceQuery.getBalance())
                : accountRepository.findByBalanceLessThan(findAccountWithBalanceQuery.getBalance());
        return bankAccountsList;
    }
}
