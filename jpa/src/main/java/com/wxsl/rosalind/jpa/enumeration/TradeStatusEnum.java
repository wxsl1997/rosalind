package com.wxsl.rosalind.jpa.enumeration;

import com.wxsl.rosalind.jpa.util.IntEnumConverter;
import com.wxsl.rosalind.jpa.util.IntEnum;
import lombok.AllArgsConstructor;

/**
 * @author wxsl1997
 */
@AllArgsConstructor
public enum TradeStatusEnum implements IntEnum {

    CREATED(21),

    PAID(22),

    FINISHED(23),

    CLOSED(24);

    final Integer value;

    @Override
    public Integer value() {
        return value;
    }

    public static class Converter implements IntEnumConverter<TradeStatusEnum> {

    }
}
