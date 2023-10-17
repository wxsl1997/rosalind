package com.wxsl.rosalind.designpattern.behavioral.visitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("访问者模式")
class CompanyTest {

    @Test
    @DisplayName("访问者测试")
    void accept() {
        Company company = new Company();
        company.add(new Employee("e1", 30, 8));
        company.add(new Employee("e2", 60, 8));
        company.add(new Employee("e3", 90, 8));

        Department department = new HrDepartment();
        company.accept(department);
    }
}
