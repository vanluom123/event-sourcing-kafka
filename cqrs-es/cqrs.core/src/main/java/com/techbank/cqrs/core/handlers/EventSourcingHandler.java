package com.techbank.cqrs.core.handlers;

import com.techbank.cqrs.core.domain.AggregateRoot;
import com.techbank.cqrs.core.exceptions.NotFoundException;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id) throws NotFoundException;
    void republishEvents() throws NotFoundException;
}
