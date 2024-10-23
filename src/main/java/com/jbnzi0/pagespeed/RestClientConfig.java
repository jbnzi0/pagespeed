package com.jbnzi0.pagespeed;

import com.jbnzi0.pagespeed.domain.clients.PageSpeedInsightsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PageSpeedInsightsClient pageSpeedInsightsClient(RestTemplate restTemplate, @Value("${google.speedinsights.url}") String baseUrl) {
        return new PageSpeedInsightsClient(restTemplate, baseUrl);
    }
}