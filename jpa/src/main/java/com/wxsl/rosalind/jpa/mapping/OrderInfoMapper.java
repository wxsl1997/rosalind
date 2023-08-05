package com.wxsl.rosalind.jpa.mapping;

import com.wxsl.rosalind.jpa.dto.OrderInfoDto;
import com.wxsl.rosalind.jpa.model.OrderInfo;
import org.mapstruct.Mapper;

/**
 * @author wxsl1997
 */
@Mapper
public interface OrderInfoMapper {
    OrderInfoDto toOrderInfoDto(OrderInfo order);
}
