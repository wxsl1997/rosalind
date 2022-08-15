package com.wxsl.rosalind.dp.behavioral.visitor;

/**
 * 具体访问者
 */
public class HrDepartment extends Department {
    @Override
    public void visit(Employee employee) {
        System.out.println("hr department:" + employee);
    }
}
