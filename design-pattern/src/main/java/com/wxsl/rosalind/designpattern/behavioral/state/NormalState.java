package com.wxsl.rosalind.designpattern.behavioral.state;

import java.math.BigDecimal;
import java.util.Objects;

public class NormalState extends BaseState {
    @Override
    public void withdraw(Account account, BigDecimal amount) {
        if (Objects.isNull(account.getBalance())) {
            account.setBalance(BigDecimal.ZERO);
        }
        account.setBalance(account.getBalance().subtract(amount));
    }

    @Override
    public void interest() {
        System.out.println("without interest");
    }
}
