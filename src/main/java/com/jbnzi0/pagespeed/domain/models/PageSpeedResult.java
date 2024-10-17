package com.jbnzi0.pagespeed.domain.models;

public record PageSpeedResult(
        String captchaResult,
        String kind,
        String id,
        LoadingExperience loadingExperience,
        LoadingExperience originLoadingExperience,
        String analysisUTCTimestamp
) {

}