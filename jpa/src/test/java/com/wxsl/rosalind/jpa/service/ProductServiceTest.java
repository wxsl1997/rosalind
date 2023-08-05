package com.wxsl.rosalind.jpa.service;

import com.google.common.collect.Iterables;
import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.jpa.command.ProductAddCommand;
import com.wxsl.rosalind.jpa.dto.ProductDescDto;
import com.wxsl.rosalind.jpa.dto.ProductDto;
import com.wxsl.rosalind.jpa.query.ProductCriteriaQuery;
import com.wxsl.rosalind.jpa.query.ProductExampleQuery;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
        List<ProductAddCommand> rows = IntStream.rangeClosed(1, ROW_NUM)
                .mapToObj(num -> ProductAddCommand.builder()
                        .name("name-" + num)
                        .description(num > 5 ? null : new ProductDescDto("desc-" + num, num, LocalDateTime.now()))
                        .stock(num)
                        .price(BigDecimal.valueOf(num))
                        .stock(num)
                        .build())
                .collect(Collectors.toList());

        productService.saveProduct(rows);
    }

    @Test
    void findByExample() {
        // match all
        List<ProductDto> products = productService.findByExample(new ProductExampleQuery());
        Assertions.assertEquals(ROW_NUM, products.size());

        ProductDto last = Iterables.getLast(products);
        // query by id
        products = productService.findByExample(ProductExampleQuery.builder().id(last.getId()).build());
        Assertions.assertEquals(last.getId(), Iterables.getOnlyElement(products).getId());

        // query by name equal
        products = productService.findByExample(ProductExampleQuery.builder().name(UUID.randomUUID().toString()).build());
        Assertions.assertTrue(products.isEmpty());
    }

    @Test
    void findByCriteria() {
        // match all
        List<ProductDto> products = productService.findByCriteria(new ProductCriteriaQuery());
        Assertions.assertEquals(ROW_NUM, products.size());

        ProductDto last = Iterables.getLast(products);
        // condition: id = ?
        products = productService.findByCriteria(ProductCriteriaQuery.builder().id(last.getId()).build());
        Assertions.assertEquals(last.getId(), Iterables.getOnlyElement(products).getId());

        // condition: name = ?
        products = productService.findByCriteria(ProductCriteriaQuery.builder().name(UUID.randomUUID().toString()).build());
        Assertions.assertTrue(products.isEmpty());

        int stock = 5;
        // condition: stock >= ?
        products = productService.findByCriteria(ProductCriteriaQuery.builder().stock(stock).build());
        Assertions.assertEquals(stock + 1, products.size());

        int price = 5;
        // condition: price between 0 and ?
        products = productService.findByCriteria(ProductCriteriaQuery.builder().price(BigDecimal.valueOf(price)).build());
        Assertions.assertEquals(price, products.size());

        // condition: stock >=? and price between 0 and ?
        products = productService.findByCriteria(ProductCriteriaQuery.builder().stock(stock).price(BigDecimal.valueOf(price)).build());
        Assertions.assertEquals(NumberUtils.INTEGER_ONE, products.size());
    }
}