package com.wxsl.rosalind.dp.structural.facade;

/**
 * 戴尔笔记本
 */
public class DellLaptop implements Laptop {

    private CPU cpu = new CPU();

    private SSD ssd = new SSD();

    private Memory memory = new Memory();

    private OS os = new OS();

    @Override
    public void startUp() {
        cpu.run();
        ssd.check();
        memory.load();
        os.start();
    }
}
