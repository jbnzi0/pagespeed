package com.jbnzi0.pagespeed.mysql.entity;

import com.jbnzi0.pagespeed.domain.models.Distribution;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name="metrics")
@Entity
public class MetricEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int percentile;

    @Getter
    @Setter
    private String category;

    @OneToMany(mappedBy = "metricEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    private List<DistributionEntity> distributions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "loading_experience_id")
    @Getter
    @Setter
    private LoadingExperienceEntity loadingExperienceEntity;

    public void setDistributions(List<DistributionEntity> distributions) {
        this.distributions.clear();
        if (distributions != null) {
            distributions.forEach(this::addDistribution);
        }
    }

    public void addDistribution(DistributionEntity distribution) {
        distributions.add(distribution);
        distribution.setMetricEntity(this);
    }

    public MetricEntity(String name, int percentile, String category, List<DistributionEntity> distributions) {
        this.name = name;
        this.percentile = percentile;
        this.category = category;
        setDistributions(distributions);
    }

    public MetricEntity() {
    }

}
