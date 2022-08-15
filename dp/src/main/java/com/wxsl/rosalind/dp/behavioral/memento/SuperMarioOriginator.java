package com.wxsl.rosalind.dp.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.cglib.beans.BeanCopier;

/**
 * 源发器
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperMarioOriginator {

    /**
     * 状态信息
     */
    private SuperMarioState state;

    /**
     * 创建备份
     */
    public SuperMarioMemento createMemento() {
        SuperMarioMemento memento = new SuperMarioMemento();

        BeanCopier beanCopier = BeanCopier.create(SuperMarioOriginator.class, SuperMarioMemento.class, false);
        beanCopier.copy(this, memento, null);

        return memento;
    }

    /**
     * 还原
     */
    public void restore(SuperMarioMemento memento) {
        this.setState(memento.getState());
    }
}
