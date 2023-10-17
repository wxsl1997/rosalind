package com.wxsl.rosalind.designpattern.behavioral.visitor;

import com.google.common.collect.Lists;
import lombok.Setter;

import java.util.List;

/**
 * 对象结构
 */
@Setter
public class Company {

    private List<Employee> employeeList = Lists.newArrayList();

    public void add(Employee employee) {
        employeeList.add(employee);
    }

    public void accept(Department handler) {
        employeeList.forEach(handler::visit);
    }
}
