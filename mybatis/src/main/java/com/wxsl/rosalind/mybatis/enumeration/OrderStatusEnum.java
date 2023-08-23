package com.wxsl.rosalind.mybatis.enumeration;

import com.wxsl.rosalind.mybatis.util.IntEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wxsl1997
 */
@Slf4j
@AllArgsConstructor
public enum OrderStatusEnum implements IntEnum {

    CREATED(11),

    FINISHED(12),

    CLOSED(13);

    final Integer value;

    @Override
    public Integer value() {
        return value;
    }
}
