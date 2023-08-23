package com.wxsl.rosalind.mybatis.converter;

import com.wxsl.rosalind.mybatis.dto.ProductDto;
import com.wxsl.rosalind.mybatis.dto.ProductInsertDto;
import com.wxsl.rosalind.mybatis.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author wxsl1997
 */
@Mapper
public interface ProductConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "version", ignore = true)
    Product toProduct(ProductInsertDto user);

    ProductDto toProductDto(Product product);
}
