package com.test.ana.loans;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Long> {
    @Query("SELECT SUM(lp.paidInstallment) FROM LoanPayment lp WHERE YEAR(lp.date) = :year AND lp.user = :user")
    Double getNetLoanPaid(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(lp.loanExtraPayment) FROM LoanPayment lp WHERE YEAR(lp.date) = :year")
    Double getLoanPaidExtra(@Param("year") int year);

    @Query("SELECT SUM(lp.loanOutstanding) FROM LoanPayment lp WHERE YEAR(lp.date) = :year")
    Double getTotalLoanOutstanding(@Param("year") int year);

    @Query("SELECT  SUM(paidInstallment) FROM LoanPayment lp WHERE lp.user = :user")
    double getTotalPaidLoanAmount(@Param("user") User user);
}
