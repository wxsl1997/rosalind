package com.wxsl.rosalind.dp.behavioral.iterator;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Spliterator;

@DisplayName("迭代器模式")
class SpliteratorTest {

    private List<String> stringList;

    @BeforeEach
    void setUp() {
        stringList = ImmutableList.<String>builder()
                .add("carola")
                .add("diana")
                .add("blueLover")
                .add("maria")
                .add("vanDeLa")
                .build();
    }

    @Test
    @DisplayName("迭代器-tryAdvance")
    void tryAdvance() {
        Spliterator<String> spliterator = stringList.spliterator();
        spliterator.tryAdvance(System.out::println);
    }

    @Test
    @DisplayName("迭代器-forEachRemaining")
    void forEachRemaining() {
        Spliterator<String> spliterator = stringList.spliterator();
        spliterator.forEachRemaining(System.out::println);
    }

    @Test
    @DisplayName("迭代器-trySplit")
    void trySplit() {
        Spliterator<String> spliterator1 = stringList.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        new Thread(() -> spliterator1.forEachRemaining(str -> System.out.println(Thread.currentThread() + ":" + str)), "thread1").start();
        new Thread(() -> spliterator2.forEachRemaining(str -> System.out.println(Thread.currentThread() + ":" + str)), "thread2").start();
    }
}
