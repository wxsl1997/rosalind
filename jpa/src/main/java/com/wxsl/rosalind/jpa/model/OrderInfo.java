package com.wxsl.rosalind.jpa.model;

import com.wxsl.rosalind.jpa.configuration.AuditableEntity;
import com.wxsl.rosalind.jpa.enumeration.OrderStatusEnum;
import com.wxsl.rosalind.jpa.util.IntEnumType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long userId;

    Long tradeId;

    Long productId;

    BigDecimal payment;

    //@Convert(converter = OrderStatusEnum.Converter.class)
    //@Type(type = "IntEnum")
    @Type(type = IntEnumType.IntEnum)
    OrderStatusEnum status;

    Integer quantity;

    @Version
    Long version;
}