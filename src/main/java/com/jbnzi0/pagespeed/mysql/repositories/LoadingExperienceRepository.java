package com.jbnzi0.pagespeed.mysql.repositories;

import com.jbnzi0.pagespeed.mysql.entity.LoadingExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadingExperienceRepository extends JpaRepository<LoadingExperienceEntity, Integer> {

    @Query("SELECT l FROM LoadingExperienceEntity l WHERE l.initialURL = ?1")
    LoadingExperienceEntity findByURL(String url);
}
