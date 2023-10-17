package com.wxsl.rosalind.designpattern.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
class SuperMarioState {

    /**
     * 关卡
     */
    private String checkPoint;

    /**
     * 存档时间
     */
    private LocalDate archiveDate;
}
