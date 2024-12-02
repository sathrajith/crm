package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Loss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LossRepository extends JpaRepository<Loss, Long> {
    List<Loss> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
