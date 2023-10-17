package com.wxsl.rosalind.designpattern.structural.adapter;

/**
 * 适配器类
 */
public class ChargerAdapter implements Charge {


    @Override
    public void charge(Device device) {
        if (Device.PHONE.equals(device)) {
            PhoneCharge.chargeForPhone();
        }

        if (Device.LAPTOP.equals(device)) {
            LaptopCharge.chargeForLaptop();
        }
    }
}
