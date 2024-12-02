package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByCustomerId(Long customerId);
}
