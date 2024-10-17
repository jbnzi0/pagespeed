package com.jbnzi0.pagespeed.controllers;

import com.jbnzi0.pagespeed.controllers.dto.PageSpeedRequestDTO;
import com.jbnzi0.pagespeed.domain.clients.PageSpeedInsightsClient;
import com.jbnzi0.pagespeed.domain.models.PageSpeedResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page_speed")
public class PageSpeedController {
    private final PageSpeedInsightsClient pageSpeedInsightsClient;

    public PageSpeedController(PageSpeedInsightsClient pageSpeedInsightsClient) {
        this.pageSpeedInsightsClient = pageSpeedInsightsClient;
    }

    @PostMapping("/measure")
    public ResponseEntity<PageSpeedResult> measure (
            @Valid @RequestBody PageSpeedRequestDTO payload
    ){
        PageSpeedResult result = pageSpeedInsightsClient.measure(payload.url(), payload.strategy());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{pageSpeedId}/loading_experience")
    public int getPageSpeed (@PathVariable int pageSpeedId) {
        return pageSpeedId;
    }
}
