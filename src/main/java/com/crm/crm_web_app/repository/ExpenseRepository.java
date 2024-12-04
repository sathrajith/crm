package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ExpenseRepository extends JpaRepository <Expense, Long> {
    @Query("SELECT SUM(e.amount) FROM Expense e")
    BigDecimal calculateTotalExpense();
}
