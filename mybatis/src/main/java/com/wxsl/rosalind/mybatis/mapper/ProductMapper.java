package com.wxsl.rosalind.mybatis.mapper;

import com.wxsl.rosalind.mybatis.configuration.EnhancedMapper;
import com.wxsl.rosalind.mybatis.configuration.MybatisTransactional;
import com.wxsl.rosalind.mybatis.entity.Product;

import java.util.List;

@MybatisTransactional(readOnly = true)
public interface ProductMapper extends EnhancedMapper<Product> {

    List<Product> findAll();
}
