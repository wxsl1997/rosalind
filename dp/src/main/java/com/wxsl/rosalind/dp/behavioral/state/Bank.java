package com.wxsl.rosalind.dp.behavioral.state;

import java.math.BigDecimal;

/**
 * 环境类
 */
public class Bank {

    /**
     * 存钱
     */
    public static void deposit(Account account, BigDecimal amount) {
        StateFactory.getState(account.getStateType()).deposit(account, amount);
        resetState(account);
    }

    /**
     * 取钱
     */
    public static void withdraw(Account account, BigDecimal amount) {
        StateFactory.getState(account.getStateType()).withdraw(account, amount);
        resetState(account);
    }

    /**
     * 利息
     */
    public static void interest(Account account) {
        StateFactory.getState(account.getStateType()).interest();
        resetState(account);
    }

    private static void resetState(Account account) {
        if (account.getBalance().compareTo(BigDecimal.valueOf(-2000L)) < 0) {
            account.setStateType(StateType.RESTRICT);
        } else if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            account.setStateType(StateType.OVERDRAFT);
        } else {
            account.setStateType(StateType.NORMAL);
        }
    }
}
