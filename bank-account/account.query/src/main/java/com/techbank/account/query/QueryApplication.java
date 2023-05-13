package com.techbank.account.query;

import com.techbank.account.query.api.queries.*;
import com.techbank.account.query.infrastructure.AccountQueryDispatcher;
import com.techbank.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {
	private final QueryDispatcher queryDispatcher;

	private final QueryHandler queryHandler;

	public QueryApplication(QueryDispatcher queryDispatcher,
							QueryHandler queryHandler) {
		this.queryDispatcher = queryDispatcher;
		this.queryHandler = queryHandler;
	}

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handleFindAllAccount);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handleFindAccountById);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handleFindAccountByHolder);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handleFindAccountWithBalance);
	}
}
