package com.wxsl.rosalind.dp.behavioral.state;

import java.math.BigDecimal;
import java.util.Objects;

public class OverdraftState extends BaseState {
    @Override
    public void withdraw(Account account, BigDecimal amount) {
        if (Objects.isNull(account.getBalance())) {
            account.setBalance(BigDecimal.ZERO);
        }
        account.setBalance(account.getBalance().subtract(amount));
    }

    @Override
    public void interest() {
        System.out.println("compute interest");
    }
}
