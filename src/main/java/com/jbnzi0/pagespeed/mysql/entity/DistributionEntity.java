package com.jbnzi0.pagespeed.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name="distributions")
@Entity
public class DistributionEntity {

    @Id
    @Getter
    private long id;

    @Getter
    @Setter
    private Integer max;

    @Getter
    @Setter
    private double proportion;
}
