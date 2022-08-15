package com.wxsl.rosalind.dp.behavioral.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("观察者模式")
class SchoolSubjectTest {

    private SchoolSubject schoolSubject;

    @BeforeEach
    void setUp() {
        schoolSubject = new SchoolSubject();
        schoolSubject.addObserver(new GoodStudent());
        schoolSubject.addObserver(new BadStudent());
    }

    @Test
    @DisplayName("观察者测试")
    void exam() {
        schoolSubject.exam();
        schoolSubject.holiday();
    }
}
