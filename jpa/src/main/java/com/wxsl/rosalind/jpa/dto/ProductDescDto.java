package com.wxsl.rosalind.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wxsl1997
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDescDto {

    String desc;

    Integer type;

    LocalDateTime time;
}
