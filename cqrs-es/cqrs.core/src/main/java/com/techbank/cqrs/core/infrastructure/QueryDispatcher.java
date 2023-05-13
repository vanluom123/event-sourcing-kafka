package com.techbank.cqrs.core.infrastructure;

import com.techbank.cqrs.core.domain.BaseEntity;
import com.techbank.cqrs.core.queries.BaseQuery;
import com.techbank.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    void registerHandler(Class<? extends BaseQuery> type, QueryHandlerMethod<? extends BaseQuery> handler);
    List<BaseEntity> send(BaseQuery query);
}
