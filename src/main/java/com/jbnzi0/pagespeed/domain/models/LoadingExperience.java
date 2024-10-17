package com.jbnzi0.pagespeed.domain.models;

import java.util.Map;

public record LoadingExperience(
        String id,
        Map<String, Metric> metrics,
        String overall_category,
        String initial_url
) {}