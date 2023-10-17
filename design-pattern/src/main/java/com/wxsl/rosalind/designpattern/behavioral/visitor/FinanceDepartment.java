package com.wxsl.rosalind.designpattern.behavioral.visitor;

/**
 * 具体访问者
 */
public class FinanceDepartment extends Department {
    @Override
    public void visit(Employee employee) {
        System.out.println("finance department:" + employee);
    }
}
