package com.wxsl.rosalind.designpattern.structural.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.wxsl.rosalind.designpattern.structural.adapter.Device.LAPTOP;
import static com.wxsl.rosalind.designpattern.structural.adapter.Device.PHONE;

@DisplayName("适配器模式")
class ChargerTest {

    @Test
    @DisplayName("适配器")
    void charge() {
        Charge charger = new ChargerAdapter();

        charger.charge(PHONE);

        charger.charge(LAPTOP);
    }
}
