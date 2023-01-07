package com.techbank.cqrs.core.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> extends ICommandHandlerMethod {
    void handle(T command);
}
