package com.wxsl.rosalind.dp.behavioral.memento;

import lombok.Getter;
import lombok.Setter;

/**
 * 备忘录
 */
@Getter
@Setter
class SuperMarioMemento {

    /**
     * 存档信息
     */
    private SuperMarioState state;
}
