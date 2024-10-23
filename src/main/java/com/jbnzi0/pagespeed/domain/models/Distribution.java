package com.jbnzi0.pagespeed.domain.models;

import java.util.Optional;

public record Distribution(
        Optional<Integer> min,
        Optional<Integer> max,
        double proportion
) {

    public int getMaxValue(){
        return max.orElse(0);
    }

    public int getMinValue(){
        return min.orElse(0);
    }
}