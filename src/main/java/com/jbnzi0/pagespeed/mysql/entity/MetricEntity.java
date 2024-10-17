package com.jbnzi0.pagespeed.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name="metrics")
@Entity
public class MetricEntity {

    @Getter
    @Id
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
}
