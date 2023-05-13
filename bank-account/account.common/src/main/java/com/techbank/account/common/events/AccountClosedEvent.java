package com.techbank.account.common.events;

import com.techbank.cqrs.core.events.BaseEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountClosedEvent extends BaseEvent {
}
