package com.wxsl.rosalind.jpa.command;

import com.wxsl.rosalind.jpa.dto.OrderInfoCreatedDto;
import lombok.Data;

import java.util.List;

/**
 * @author wxsl1997
 */
@Data
public class TradeCreatedCommand {

    List<OrderInfoCreatedDto> orders;
}
