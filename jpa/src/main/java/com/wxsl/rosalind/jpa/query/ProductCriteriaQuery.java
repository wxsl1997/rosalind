package com.wxsl.rosalind.jpa.query;

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
public class ProductCriteriaQuery {

    Long id;

    String name;

    BigDecimal price;

    Integer stock;
}
