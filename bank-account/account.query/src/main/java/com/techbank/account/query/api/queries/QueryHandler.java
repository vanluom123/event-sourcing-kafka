package com.techbank.account.query.api.queries;

import com.techbank.cqrs.core.domain.BaseEntity;
import com.techbank.cqrs.core.queries.BaseQuery;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handleFindAllAccount(BaseQuery query);
    List<BaseEntity> handleFindAccountById(BaseQuery query);
    List<BaseEntity> handleFindAccountByHolder(BaseQuery query);
    List<BaseEntity> handleFindAccountWithBalance(BaseQuery query);
}
