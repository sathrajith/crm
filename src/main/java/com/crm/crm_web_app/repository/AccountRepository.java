package com.crm.crm_web_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.crm_web_app.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Find accounts by status
    List<Account> findByStatus(String status);
}

