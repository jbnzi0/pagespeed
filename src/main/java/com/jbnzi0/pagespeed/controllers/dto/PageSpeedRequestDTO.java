package com.jbnzi0.pagespeed.controllers.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PageSpeedRequestDTO(
        @NotBlank(message = "URL is required")
        @Size(max = 2048, message = "URL must not exceed 2048 characters")
        @Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$",
                message = "Invalid URL format")
        String url,

        @NotBlank(message = "Strategy is required")
        @Pattern(regexp = "^(mobile|desktop)$", message = "Strategy must be either 'mobile' or 'desktop'")
        String strategy
) {}

