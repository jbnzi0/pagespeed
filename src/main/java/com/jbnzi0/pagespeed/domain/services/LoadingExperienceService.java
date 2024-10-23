package com.jbnzi0.pagespeed.domain.services;

import com.jbnzi0.pagespeed.domain.models.Distribution;
import com.jbnzi0.pagespeed.domain.models.LoadingExperience;
import com.jbnzi0.pagespeed.domain.models.Metric;
import com.jbnzi0.pagespeed.mysql.entity.DistributionEntity;
import com.jbnzi0.pagespeed.mysql.entity.LoadingExperienceEntity;
import com.jbnzi0.pagespeed.mysql.entity.MetricEntity;
import com.jbnzi0.pagespeed.mysql.repositories.LoadingExperienceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoadingExperienceService {

    private final LoadingExperienceRepository loadingExperienceRepository;
    private final URLService urlService;

    public LoadingExperienceEntity findLoadingExperienceByURL(String url) {
        System.out.println("url" + url);
        String normalizedURL = urlService.normalizeURL(url);
        System.out.println("normalizedURL" + normalizedURL);

        return loadingExperienceRepository.findByURL(normalizedURL);
    }

    @Transactional
    public LoadingExperienceEntity createLoadingExperience(LoadingExperience loadingExperience) {
        List<MetricEntity> metrics = formatMetrics(loadingExperience.metrics());
        String normalizedURL = urlService.normalizeURL(loadingExperience.initial_url());

        LoadingExperienceEntity entity = new LoadingExperienceEntity(loadingExperience.overall_category(), normalizedURL, metrics);

        return loadingExperienceRepository.save(entity);
    }

    List<MetricEntity> formatMetrics(Map<String, Metric> metricMap) {
        return metricMap.entrySet().stream().map(this::formatMetric).toList();
    }

    private MetricEntity formatMetric(Map.Entry<String, Metric> entry) {
        Metric metric = entry.getValue();
        String metricName = entry.getKey();
        List<DistributionEntity> distributions = formatDistributions(metric.distributions());
        return new MetricEntity(metricName, metric.percentile(), metric.category(), distributions);

    }

    private List<DistributionEntity> formatDistributions(List<Distribution> distributions) {
        return distributions.stream().map(distribution -> new DistributionEntity(distribution.getMaxValue(), distribution.getMinValue(), distribution.proportion())).toList();
    }

}



