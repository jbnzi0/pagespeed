package com.jbnzi0.pagespeed.domain.clients;

import com.jbnzi0.pagespeed.domain.models.PageSpeedResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PageSpeedInsightsClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PageSpeedInsightsClient(RestTemplate restTemplate, @Value("${google.speedinsights.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }
    public PageSpeedResult measure(String url, String strategy) {
        String apiPath = baseUrl + "/pagespeedonline/v5/runPagespeed?url=" + url + "&strategy=" + strategy;
        return restTemplate.getForObject(apiPath, PageSpeedResult.class);
    }
}

