package com.wxsl.rosalind.mybatis.converter;

import com.wxsl.rosalind.mybatis.dto.OrderInfoDto;
import com.wxsl.rosalind.mybatis.entity.OrderInfo;
import org.mapstruct.Mapper;

/**
 * @author wxsl1997
 */
@Mapper
public interface OrderInfoConverter {

    OrderInfoDto toOrderInfoDto(OrderInfo orderInfo);
}
