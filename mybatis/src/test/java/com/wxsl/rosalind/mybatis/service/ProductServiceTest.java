package com.wxsl.rosalind.mybatis.service;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.mybatis.dto.ProductDto;
import com.wxsl.rosalind.mybatis.dto.ProductInsertDto;
import com.wxsl.rosalind.mybatis.dto.UpdateDescDto;
import com.wxsl.rosalind.mybatis.util.StreamUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
class ProductServiceTest extends BaseTest {

    public static final int ROW_NUM = 10;

    @Resource
    ProductService productService;

    @BeforeEach
    public void init() {
        List<ProductInsertDto> rows = IntStream.rangeClosed(1, ROW_NUM)
                .mapToObj(num -> ProductInsertDto.builder()
                        .name("name-" + num)
//                        .description(num > 5 ? null : new ProductDescDto("desc-" + num, num, LocalDateTime.now()))
                        .description("desc-" + num)
                        .stock(num)
                        .price(BigDecimal.valueOf(num))
                        .stock(num)
                        .build())
                .collect(Collectors.toList());

        productService.insertOrUpdate(rows);
    }

    @Test
    void updateDesc() {
        List<ProductDto> products = productService.selectAll();
        List<UpdateDescDto> updateDiscs = StreamUtils.map(products, product -> new UpdateDescDto(product.getId(), "new-desc-" + product.getId()));
        productService.updateDesc(updateDiscs);
    }

}