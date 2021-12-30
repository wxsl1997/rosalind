package com.wxsl.rosalind.framework.web.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@RestController
@RequestMapping("/converter")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConverterController {

    @GetMapping("test-date")
    public LocalDate testDate(LocalDate date) {
        return date;
    }

    @GetMapping("test-date2")
    public LocalDateTime testDate(LocalDateTime date) {
        return date;
    }

}
