package com.techbank.account.cmd.api.commands;

import com.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}
