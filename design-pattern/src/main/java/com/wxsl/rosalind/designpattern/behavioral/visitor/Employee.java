package com.wxsl.rosalind.designpattern.behavioral.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 元素
 */
@Data
@AllArgsConstructor
public class Employee {

    private String name;

    private Integer hourlyPay;

    private Integer workingHour;
}
