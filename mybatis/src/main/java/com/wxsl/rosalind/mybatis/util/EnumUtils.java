package com.wxsl.rosalind.mybatis.util;

import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class EnumUtils {

    /**
     * int -> enum 缓存
     */
    public static Map<Class<? extends IntEnum>, Map<Integer, Enum<?>>> INT_ENUM_CACHE = Maps.newConcurrentMap();

    /**
     * code -> enum 缓存
     */
    public static Map<Class<? extends CodeEnum>, Map<String, Enum<?>>> CODE_ENUM_CACHE = Maps.newConcurrentMap();


    /**
     * IntEnum 接口, 根据 code 找 Enum, 没有返回 null
     */
    public static <E extends Enum<E> & IntEnum> E valueToEnum(Class<E> clazz, Integer value) {
        Map<Integer, Enum<?>> descToEnumMap = INT_ENUM_CACHE.computeIfAbsent(clazz, (key) -> {
            List<IntEnum> enumNames = new ArrayList<>(EnumSet.allOf(clazz));
            Map<Integer, IntEnum> enumNameMap = Maps.uniqueIndex(enumNames, IntEnum::value);

            Map<Integer, Enum<?>> enumMap = Maps.newHashMap();
            enumNameMap.forEach((k, v) -> enumMap.put(k, (Enum<?>) v));
            return enumMap;
        });
        //noinspection unchecked
        return (E) Optional.ofNullable(value).map(descToEnumMap::get).orElse(null);
    }

    /**
     * CodeEnum 接口, 根据 code 找 Enum, 没有返回 null
     */
    public static <T extends Enum<T> & CodeEnum> T codeToEnum(Class<T> clazz, String code) {

        Map<String, Enum<?>> codeToEnumMap = CODE_ENUM_CACHE.computeIfAbsent(clazz, (key) -> {
            List<CodeEnum> enumCodes = new ArrayList<>(EnumSet.allOf(clazz));

            Map<String, CodeEnum> enumCodeMap = Maps.uniqueIndex(enumCodes, CodeEnum::code);

            Map<String, Enum<?>> enumMap = Maps.newHashMap();
            enumCodeMap.forEach((k, v) -> enumMap.put(k, (Enum<?>) v));
            return enumMap;
        });
        //noinspection unchecked
        return Optional.ofNullable(code).map(Object::toString).map(v -> (T) codeToEnumMap.get(v)).orElse(null);
    }

    /**
     * value -> code
     */
    public static <T extends Enum<T> & CodeEnum & IntEnum> String valueToCode(Class<T> clazz, Integer value) {
        CodeEnum codeEnum = valueToEnum(clazz, value);
        return Optional.ofNullable(codeEnum).map(CodeEnum::code).orElse(null);
    }

    /**
     * code -> value
     */
    public static <T extends Enum<T> & CodeEnum & IntEnum> Integer codeToValue(Class<T> clazz, String code) {
        IntEnum intEnum = codeToEnum(clazz, code);
        return Optional.ofNullable(intEnum).map(IntEnum::value).orElse(null);
    }
}
