package com.techbank.account.cmd.api.commands;

import com.techbank.account.cmd.common.exception.ResourceNotFoundException;
import com.techbank.account.cmd.domain.AccountAggregate;
import com.techbank.cqrs.core.exceptions.NotFoundException;
import com.techbank.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.stereotype.Service;

// The 'ConcreteColleague' class
@Service
public class AccountCommandHandler implements CommandHandler {
    private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    public AccountCommandHandler(EventSourcingHandler<AccountAggregate> eventSourcingHandler) {
        this.eventSourcingHandler = eventSourcingHandler;
    }

    @Override
    public void handle(OpenAccountCommand command) {
        var aggregate = new AccountAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DepositFundsCommand command) {
        AccountAggregate aggregate;
        try {
            aggregate = eventSourcingHandler.getById(command.getId());
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e);
        }
        aggregate.depositFunds(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        AccountAggregate aggregate;
        try {
            aggregate = eventSourcingHandler.getById(command.getId());
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e);
        }
        if (command.getAmount() > aggregate.getBalance()) {
            throw new IllegalStateException("Withdrawal declined, insufficient funds!");
        }
        aggregate.withdrawFunds(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        AccountAggregate aggregate;
        try {
            aggregate = eventSourcingHandler.getById(command.getId());
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e);
        }
        aggregate.closeAccount();
        eventSourcingHandler.save((aggregate));
    }

    @Override
    public void handle(RestoreReadDbCommand command) {
        try {
            eventSourcingHandler.republishEvents();
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(e);
        }
    }
}
