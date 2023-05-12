package com.techbank.account.cmd.infrastructure;

import com.techbank.account.cmd.common.Messages;
import com.techbank.account.cmd.common.exception.CommandException;
import com.techbank.cqrs.core.commands.BaseCommand;
import com.techbank.cqrs.core.commands.CommandHandlerMethod;
import com.techbank.cqrs.core.commands.ICommandHandlerMethod;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<? extends BaseCommand>, List<ICommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseCommand> void send(T command) {
        var handlers = routes.get(command.getClass());
        if (handlers == null || handlers.size() == 0) {
            throw new CommandException(Messages.NO_COMMAND_HANDLER_REGISTERED);
        }
        if (handlers.size() > 1) {
            throw new CommandException(Messages.CANNOT_SEND_COMMAND);
        }
        CommandHandlerMethod<T> handler;
        try
        {
            handler = (CommandHandlerMethod<T>)handlers.get(0);
            handler.handle(command);
        }
        catch (ClassCastException e) {
            throw new ClassCastException(Messages.CAST_ERROR);
        }
    }
}
