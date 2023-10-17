package com.wxsl.rosalind.designpattern.behavioral.memento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("备忘录模式")
class SuperMarioOriginatorTest {

    private SuperMarioCaretaker caretaker;

    private static final LocalDate archiveDate = LocalDate.of(2010, 10, 10);

    @BeforeEach
    void setUp() {
        caretaker = new SuperMarioCaretaker();
        SuperMarioOriginator originator = new SuperMarioOriginator(new SuperMarioState("2-1", archiveDate));
        //添加存档
        caretaker.backup("2-1", originator.createMemento());
    }

    @Test
    @DisplayName("备忘录测试")
    void restore() {
        SuperMarioOriginator originator = new SuperMarioOriginator();
        originator.restore(caretaker.getMemento("2-1"));
        Assertions.assertEquals(archiveDate, originator.getState().getArchiveDate());
    }
}
