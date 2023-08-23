package com.wxsl.rosalind.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxsl1997
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDescDto {

    Long id;

    String desc;
}
