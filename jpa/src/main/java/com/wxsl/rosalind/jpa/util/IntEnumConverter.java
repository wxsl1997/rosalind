package com.wxsl.rosalind.jpa.util;

import org.springframework.core.GenericTypeResolver;

import javax.persistence.AttributeConverter;

public interface IntEnumConverter<E extends Enum<E> & IntEnum> extends AttributeConverter<E, Integer> {
    @Override
    default Integer convertToDatabaseColumn(E attribute) {
        return attribute.value();
    }

    @Override
    default E convertToEntityAttribute(Integer dbData) {
        // convert not guarantee execute order, convert to entity attribute may execute in front
        Class<?> clazz = GenericTypeResolver.resolveTypeArgument(getClass(), IntEnumConverter.class);
        //noinspection unchecked
        return EnumUtils.valueToEnum((Class<E>) clazz, dbData);
    }
}