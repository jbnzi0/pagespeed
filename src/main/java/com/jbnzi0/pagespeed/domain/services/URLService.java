package com.jbnzi0.pagespeed.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class URLService {

    public String normalizeURL(String url) {
        String normalized = url.toLowerCase();
        if (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }

        if (normalized.startsWith("www.")) {
            normalized = normalized.substring(4);
        }

        if (!normalized.startsWith("http")) {
            normalized = "https://" + normalized;
        }

        return normalized;
    }
}
