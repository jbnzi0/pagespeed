package com.jbnzi0.pagespeed.mysql.repositories;

import com.jbnzi0.pagespeed.mysql.entity.LoadingExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadingExperienceRepository extends JpaRepository<LoadingExperienceEntity, Integer> {

    LoadingExperienceEntity findByInitialURL(String initialURL);
}
