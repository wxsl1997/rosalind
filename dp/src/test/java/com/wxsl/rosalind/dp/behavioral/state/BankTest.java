package com.wxsl.rosalind.dp.behavioral.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@DisplayName("状态模式")
class BankTest {

    @Test
    @DisplayName("状态测试")
    void deposit() {
        Account account = new Account.AccountBuilder()
                .name("xxx")
                .stateType(StateType.NORMAL)
                .build();
        Bank.deposit(account, new BigDecimal(1_000_000_000L));
        Assertions.assertEquals(StateType.NORMAL, account.getStateType());
    }

    @Test
    @DisplayName("状态测试")
    void withdraw() {
        Account account = new Account.AccountBuilder()
                .name("xxx")
                .stateType(StateType.NORMAL)
                .build();
        {
            Bank.withdraw(account, new BigDecimal(1_000L));
            Assertions.assertEquals(StateType.OVERDRAFT, account.getStateType());
        }
        {
            Bank.withdraw(account, new BigDecimal(999_999_000L));
            Assertions.assertEquals(StateType.RESTRICT, account.getStateType());
        }
    }
}
