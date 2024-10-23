package com.jbnzi0.pagespeed.controllers;

import com.jbnzi0.pagespeed.controllers.dto.PageSpeedRequestDTO;
import com.jbnzi0.pagespeed.domain.clients.PageSpeedInsightsClient;
import com.jbnzi0.pagespeed.domain.models.PageSpeedResult;
import com.jbnzi0.pagespeed.domain.services.LoadingExperienceService;
import com.jbnzi0.pagespeed.mysql.entity.LoadingExperienceEntity;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page_speed")
@RequiredArgsConstructor
public class PageSpeedController {
    private final PageSpeedInsightsClient pageSpeedInsightsClient;

    @Resource
    private final LoadingExperienceService loadingExperienceService;

    @PostMapping("/measure")
    public ResponseEntity<LoadingExperienceEntity> measure(
            @Valid @RequestBody PageSpeedRequestDTO payload
    ) {
        LoadingExperienceEntity existingEntity = loadingExperienceService.findLoadingExperienceByURL(payload.url());

        System.out.println("existingEntity = " + existingEntity);
        if (existingEntity != null) {
            return ResponseEntity.ok(existingEntity);
        }

        PageSpeedResult result = pageSpeedInsightsClient.measure(payload.url(), payload.strategy());
        System.out.println("result = " + result);
        LoadingExperienceEntity experienceEntity = loadingExperienceService.createLoadingExperience(result.loadingExperience());
        return ResponseEntity.ok(experienceEntity);
    }

    @GetMapping()
    public ResponseEntity<LoadingExperienceEntity> getPageSpeed(@RequestParam String url) {

        LoadingExperienceEntity existingEntity = loadingExperienceService.findLoadingExperienceByURL(url);

        System.out.println("existingEntity = " + existingEntity);
        if (existingEntity == null) {
            return ResponseEntity.notFound().build();
        }

            return ResponseEntity.ok(existingEntity);
    }
}
