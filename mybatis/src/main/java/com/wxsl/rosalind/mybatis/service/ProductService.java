package com.wxsl.rosalind.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wxsl.rosalind.mybatis.configuration.MybatisTransactional;
import com.wxsl.rosalind.mybatis.converter.ProductConverter;
import com.wxsl.rosalind.mybatis.dto.ProductDescDto;
import com.wxsl.rosalind.mybatis.dto.ProductDto;
import com.wxsl.rosalind.mybatis.dto.ProductInsertDto;
import com.wxsl.rosalind.mybatis.dto.UpdateDescDto;
import com.wxsl.rosalind.mybatis.entity.Product;
import com.wxsl.rosalind.mybatis.mapper.ProductMapper;
import com.wxsl.rosalind.mybatis.util.StreamUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author wxsl1997
 */
@Service
public class ProductService {

    @Resource
    ProductMapper productMapper;

    @Resource
    ProductConverter productConverter;

    @MybatisTransactional
    public void insertOrUpdate(List<ProductInsertDto> dtos) {

        if (CollectionUtils.isEmpty(dtos)) {
            return;
        }

        List<Product> dbProducts = productMapper.selectList(new LambdaQueryWrapper<Product>().in(Product::getName, StreamUtils.map(dtos, ProductInsertDto::getName)));

        Map<String, Product> nameToProductMap = StreamUtils.toMap(dbProducts, Product::getName, Function.identity());

        dtos.forEach(dto -> {
            Product product = productConverter.toProduct(dto);

            nameToProductMap.merge(dto.getName(), product, (oldValue, newValue) -> {
                oldValue.setPrice(newValue.getPrice());
                return oldValue;
            });
        });

        // insert or update
        productMapper.saveOrUpdateBatch(nameToProductMap.values());
    }

    @MybatisTransactional
    public void updateDesc(List<UpdateDescDto> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return;
        }

        Map<Long, ProductDescDto> toToDescMap = StreamUtils.putToMap(dtos, UpdateDescDto::getId, UpdateDescDto::getDesc);

        List<Product> products = productMapper.selectBatchIds(StreamUtils.map(dtos, UpdateDescDto::getId));
        products.forEach(product -> product.setDescription(toToDescMap.get(product.getId())));
        // batch update
        productMapper.updateBatchByIds(products);
    }

    public List<ProductDto> selectAll() {
        List<Product> products = productMapper.findAll();
        return StreamUtils.map(products, product -> productConverter.toProductDto(product));
    }
}
