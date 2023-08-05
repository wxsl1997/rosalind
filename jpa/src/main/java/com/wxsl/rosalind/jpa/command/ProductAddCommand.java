package com.wxsl.rosalind.jpa.command;

import com.wxsl.rosalind.jpa.dto.ProductDescDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddCommand {

    String name;

    ProductDescDto description;

    BigDecimal price;

    Integer stock;
}
