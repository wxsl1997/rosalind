package com.wxsl.rosalind.jpa.model;

import com.wxsl.rosalind.jpa.configuration.AuditableEntity;
import com.wxsl.rosalind.jpa.enumeration.TradeStatusEnum;
import lombok.*;

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
public class TradeInfo extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long userId;

    BigDecimal payment;

    @Convert(converter = TradeStatusEnum.Converter.class)
    //@Type(type = IntEnumType.TYPE)
    TradeStatusEnum status;

    @Version
    Long version;
}