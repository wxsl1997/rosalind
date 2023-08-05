package com.wxsl.rosalind.jpa.model;

import com.wxsl.rosalind.jpa.configuration.AuditableEntity;
import com.wxsl.rosalind.jpa.dto.ProductDescDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Type(type = "Json")
    ProductDescDto description;

    BigDecimal price;

    Integer stock;

    @Version
    Long version;
}