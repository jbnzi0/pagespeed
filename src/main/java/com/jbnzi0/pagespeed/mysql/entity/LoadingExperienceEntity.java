package com.jbnzi0.pagespeed.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
