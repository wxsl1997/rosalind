package com.wxsl.rosalind.mybatis.command;

import com.wxsl.rosalind.mybatis.dto.OrderInfoCreatedDto;
import lombok.Data;

import java.util.List;

/**
 * @author wxsl1997
 */
@Data
public class TradeCreatedCommand {

    List<OrderInfoCreatedDto> orders;
}
