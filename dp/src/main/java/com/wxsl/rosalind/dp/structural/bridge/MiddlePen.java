package com.wxsl.rosalind.dp.structural.bridge;

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
