package com.jbnzi0.pagespeed.domain.models;

import java.util.List;

public record Metric(
        int percentile,
        List<Distribution> distributions,
        String category
) {}
