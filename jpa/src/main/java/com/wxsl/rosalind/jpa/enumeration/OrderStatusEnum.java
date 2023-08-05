package com.wxsl.rosalind.jpa.enumeration;

import com.wxsl.rosalind.jpa.util.IntEnum;
import com.wxsl.rosalind.jpa.util.IntEnumConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wxsl1997
 */
@Slf4j
@AllArgsConstructor
public enum OrderStatusEnum  implements IntEnum {

    CREATED(11),

    FINISHED(12),

    CLOSED(13);

    final Integer value;

    @Override
    public Integer value() {
        return value;
    }

    public static class Converter implements IntEnumConverter<OrderStatusEnum> {

    }
}
