package com.project.expensetracker_api.repository;

import com.project.expensetracker_api.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<List<Expense>> findByCustomerId(Long customerId);

    @Query("SELECT e FROM Expense e WHERE e.createdDate >= :sevenDaysAgo AND e.customer.id = :customerId")
    List<Expense> findLast7Days(@Param("sevenDaysAgo") LocalDateTime sevenDaysAgo, @Param("customerId") Long customerId);

    @Query("SELECT e FROM Expense e WHERE e.createdDate >= :thirtyDaysAgo AND e.customer.id = :customerId")
    List<Expense> findLast30Days(@Param("thirtyDaysAgo") LocalDateTime thirtyDaysAgo, @Param("customerId") Long customerId);

    List<Expense> findAllByCustomerId(Long customerId);


}
