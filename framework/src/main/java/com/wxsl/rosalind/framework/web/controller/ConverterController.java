package com.wxsl.rosalind.framework.web.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@RestController
@RequestMapping("/converter")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConverterController {

    @GetMapping("test-long")
    public Long testLong(Long value) {
        return Long.MAX_VALUE;
    }

    @GetMapping("test-date")
    public LocalDate testDate(LocalDate date) {
        return date;
    }

    @GetMapping("test-date-time")
    public LocalDateTime testDate2(LocalDateTime date) {
        return date;
    }

    @GetMapping("test-last-second")
    public LocalDateTime testDate3(LastSecondQuery query) {
        return query.getDate();
    }

    @PostMapping("test-last-second")
    public LastSecondCommand testDate4(@RequestBody LastSecondCommand query) {
        return query;
    }
}
