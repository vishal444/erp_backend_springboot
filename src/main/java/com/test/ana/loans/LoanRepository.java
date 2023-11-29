package com.test.ana.loans;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT  SUM(totalAmount) FROM Loan l WHERE l.user = :user")
    double getTotalSumLoans(@Param("user") User user);
    @Query("SELECT l FROM Loan l WHERE l.user = :user")
    List<Loan> findLoansByUser(@Param("user") User user);
}
