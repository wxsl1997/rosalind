package com.wxsl.rosalind.dp.behavioral.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 环境类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggerContext {

    /**
     * 维系策略对象引用
     */
    private LogStrategy logStrategy;

    public void log(String data) {
        logStrategy.display(data);
    }
}
