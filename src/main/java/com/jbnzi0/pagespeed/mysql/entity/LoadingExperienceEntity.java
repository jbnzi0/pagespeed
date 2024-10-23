package com.jbnzi0.pagespeed.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="loading_experiences")
@Entity
public class LoadingExperienceEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Getter
    @Setter
    private String overallCategory;

    @Getter
    @Setter
    private String initialURL;

    @OneToMany(mappedBy = "loadingExperienceEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    private List<MetricEntity> metrics = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void setMetrics(List<MetricEntity> metrics) {
        this.metrics.clear();
        if (metrics != null) {
            metrics.forEach(this::addMetric);
        }
    }

    public void addMetric(MetricEntity metric) {
        metrics.add(metric);
        metric.setLoadingExperienceEntity(this);
    }


    public LoadingExperienceEntity() {
    }

    public LoadingExperienceEntity(String overallCategory, String initialURL, List<MetricEntity> metrics) {
        this.overallCategory = overallCategory;
        this.initialURL = initialURL;
        setMetrics(metrics);
    }
}
