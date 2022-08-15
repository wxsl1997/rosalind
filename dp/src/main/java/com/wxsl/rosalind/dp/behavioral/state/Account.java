package com.wxsl.rosalind.dp.behavioral.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
class Account {

    private String name;

    private BigDecimal balance;

    private StateType stateType;
}
