package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.CommandHandler;
import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.cmd.api.commands.WithdrawFundsCommand;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggregateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/withdrawFunds")
public class WithdrawFundsController {
    private final Logger logger = Logger.getLogger(WithdrawFundsController.class.getName());

    private final CommandDispatcher commandDispatcher;
    private final CommandHandler commandHandler;

    public WithdrawFundsController(CommandDispatcher commandDispatcher, CommandHandler commandHandler) {
        this.commandDispatcher = commandDispatcher;
        this.commandHandler = commandHandler;
    }

    @PostConstruct
    public void registerHandler() {
        commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id,
                                                     @RequestBody WithdrawFundsCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Withdraw funds request completed successfully!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to withdraw funds from bank account with id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}