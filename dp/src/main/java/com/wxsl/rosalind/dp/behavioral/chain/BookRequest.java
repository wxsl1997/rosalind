package com.wxsl.rosalind.dp.behavioral.chain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 请求
 */
@Getter
@Setter
public class BookRequest {
    private Map<Level, List<Book>> levelListMap;
}
