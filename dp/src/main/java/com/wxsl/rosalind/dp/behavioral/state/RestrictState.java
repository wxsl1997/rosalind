package com.wxsl.rosalind.dp.behavioral.state;

import java.math.BigDecimal;

public class RestrictState extends BaseState {
    @Override
    public void withdraw(Account account, BigDecimal amount) {
        System.out.println("restricted profiles");
    }

    @Override
    public void interest() {
        System.out.println("compute interest");
    }
}
