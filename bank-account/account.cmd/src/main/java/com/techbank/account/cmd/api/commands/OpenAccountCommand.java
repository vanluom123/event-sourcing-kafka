package com.techbank.account.cmd.api.commands;

import com.techbank.account.common.dto.AccountType;
import com.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}