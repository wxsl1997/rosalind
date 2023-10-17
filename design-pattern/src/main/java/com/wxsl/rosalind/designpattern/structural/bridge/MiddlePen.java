package com.wxsl.rosalind.designpattern.structural.bridge;

import lombok.NoArgsConstructor;

@NoArgsConstructor
class MiddlePen extends Pen {

    public MiddlePen(Color color) {
        super(color);
    }

    @Override
    protected String size() {
        return "middle";
    }
}
