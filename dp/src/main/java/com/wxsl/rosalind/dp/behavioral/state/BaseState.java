package com.wxsl.rosalind.dp.behavioral.state;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 抽象状态类
 */
public abstract class BaseState {

    public void deposit(Account account, BigDecimal amount) {
        if (Objects.isNull(account.getBalance())) {
            account.setBalance(BigDecimal.ZERO);
        }
        account.setBalance(account.getBalance().add(amount));
    }

    public abstract void withdraw(Account account, BigDecimal amount);

    public abstract void interest();
}
