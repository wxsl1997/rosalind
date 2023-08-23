package com.wxsl.rosalind.mybatis.util;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author wxsl1997
 */
public class StreamUtils {

    public static <T> List<T> filter(Collection<T> rows, Predicate<? super T> predicate) {
        return rows.stream().filter(predicate).collect(Collectors.toList());
    }


    public static <T, R> List<R> map(Collection<T> rows, Function<? super T, ? extends R> mapper) {
        return rows.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> mapAsSet(Collection<T> rows, Function<? super T, ? extends R> mapper) {
        return rows.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T, K, U> Map<K, U> toMap(Collection<T> rows,
                                            Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends U> valueMapper
    ) {
        return rows.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T, K, U> Map<K, U> toMap(Collection<T> rows,
                                            Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends U> valueMapper,
                                            BinaryOperator<U> mergeFunction
    ) {
        return rows.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    public static <T, K, U> Map<K, U> putToMap(Collection<T> rows,
                                               Function<? super T, ? extends K> keyMapper,
                                               Function<? super T, ? extends U> valueMapper) {
        HashMap<K, U> result = Maps.newHashMap();
        rows.forEach(row -> result.put(keyMapper.apply(row), valueMapper.apply(row)));
        return result;
    }

    public static <T, K> Map<K, List<T>> groupBy(Collection<T> rows, Function<? super T, ? extends K> keyMapper) {
        return rows.stream().collect(Collectors.groupingBy(keyMapper, Collectors.toList()));
    }

    public static <T> boolean anyMatch(Collection<T> rows, Predicate<? super T> predicate) {
        return rows.stream().anyMatch(predicate);
    }

    public static <T> boolean allMatch(Collection<T> rows, Predicate<? super T> predicate) {
        return rows.stream().allMatch(predicate);
    }

    public static <T> boolean noneMatch(Collection<T> rows, Predicate<? super T> predicate) {
        return rows.stream().noneMatch(predicate);
    }
}
