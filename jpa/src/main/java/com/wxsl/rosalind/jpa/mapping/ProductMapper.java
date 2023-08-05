package com.wxsl.rosalind.jpa.mapping;

import com.wxsl.rosalind.jpa.command.ProductAddCommand;
import com.wxsl.rosalind.jpa.dto.ProductDto;
import com.wxsl.rosalind.jpa.model.Product;
import com.wxsl.rosalind.jpa.query.ProductExampleQuery;
import org.mapstruct.Mapper;

/**
 * @author wxsl1997
 */
@Mapper
public interface ProductMapper {
    Product toProduct(ProductExampleQuery example);

    ProductDto toProductDto(Product row);

    Product toProduct(ProductAddCommand dtos);
}
