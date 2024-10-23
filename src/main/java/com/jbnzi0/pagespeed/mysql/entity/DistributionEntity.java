package com.jbnzi0.pagespeed.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name="distributions")
@Entity
public class DistributionEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private int max;

    @Getter
    @Setter
    private int min;

    @Getter
    @Setter
    private double proportion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_id")
    @Getter @Setter
    private MetricEntity metricEntity;

    public DistributionEntity() {
    }

    public DistributionEntity(int max, int min, double proportion) {
        this.max = max;
        this.min = min;
        this.proportion = proportion;
    }
}
