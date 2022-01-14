package com.wxsl.rosalind.framework.web.controller;

import com.wxsl.rosalind.framework.web.annotation.LastSecond;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LastSecondCommand {

    @LastSecond
    LocalDateTime date;
}
