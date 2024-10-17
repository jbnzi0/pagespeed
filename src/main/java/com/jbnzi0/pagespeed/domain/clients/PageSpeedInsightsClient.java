package com.jbnzi0.pagespeed.domain.clients;

import com.jbnzi0.pagespeed.domain.models.PageSpeedResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PageSpeedInsightsClient {
    private final RestClient restClient;

    public PageSpeedInsightsClient(RestClient.Builder restClientBuilder,
                                   @Value("${google.speedinsights.url}") String baseUrl) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
    }

    public PageSpeedResult measure(String url, String strategy) {
        String apiPath = "/pagespeedonline/v5/runPagespeed";

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiPath)
                        .queryParam("url", url)
                        .queryParam("strategy", strategy)
                        .build())
                .retrieve()
                .body(PageSpeedResult.class);
    }
}

