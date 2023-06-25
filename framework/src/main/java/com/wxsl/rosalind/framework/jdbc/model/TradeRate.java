package com.wxsl.rosalind.framework.jdbc.model;

import com.wxsl.rosalind.framework.jdbc.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TradeRate extends AuditableEntity {

    /**
     * 子订单 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    /**
     * 交易 ID
     */
    private Long tid;

    /**
     * 评价者昵称
     */
    private String nick;

    /**
     * 评价结果
     */
    private String result;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价解释
     */
    private String reply;

    @Version
    private Long version;
}
