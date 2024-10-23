package com.jbnzi0.pagespeed.controllers;

import com.jbnzi0.pagespeed.controllers.dto.PageSpeedRequestDTO;
import com.jbnzi0.pagespeed.domain.clients.PageSpeedInsightsClient;
import com.jbnzi0.pagespeed.domain.models.ErrorResponse;
import com.jbnzi0.pagespeed.domain.models.PageSpeedResult;
import com.jbnzi0.pagespeed.domain.models.exceptions.LoadingExperienceNotFoundException;
import com.jbnzi0.pagespeed.domain.services.LoadingExperienceService;
import com.jbnzi0.pagespeed.mysql.entity.LoadingExperienceEntity;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PageSpeedController {
    private final PageSpeedInsightsClient pageSpeedInsightsClient;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleLoadingExperienceNotFoundException(LoadingExperienceNotFoundException e) {
        ErrorResponse error = new ErrorResponse();
                error.setMessage(e.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Resource
    private final LoadingExperienceService loadingExperienceService;

    @PostMapping("/page_speed/measure")
    public ResponseEntity<LoadingExperienceEntity> measure(
            @Valid @RequestBody PageSpeedRequestDTO payload
    ) {
        LoadingExperienceEntity existingEntity = loadingExperienceService.findLoadingExperienceByURL(payload.url());

        if (existingEntity != null) {
            return ResponseEntity.ok(existingEntity);
        }

        PageSpeedResult result = pageSpeedInsightsClient.measure(payload.url(), payload.strategy());
        LoadingExperienceEntity experienceEntity = loadingExperienceService.createLoadingExperience(result.loadingExperience());
        return ResponseEntity.ok(experienceEntity);
    }

    @GetMapping("/page_speed")
    public ResponseEntity<LoadingExperienceEntity> getPageSpeed(@RequestParam String url) {

        LoadingExperienceEntity loadingExperience = loadingExperienceService.findLoadingExperienceByURL(url);

        if (loadingExperience == null) {
            throw new LoadingExperienceNotFoundException("Loading experience not found for url: " + url);
        }

            return ResponseEntity.ok(loadingExperience);
    }
}
