package com.test.ana.overDraft;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ODPaymentRepository extends JpaRepository<ODPayment, Long> {
    @Query("SELECT SUM(op.odRepaid) FROM ODPayment op WHERE YEAR(op.date) = :year AND op.user = :user")
    Double getTotalODRepaid(@Param("year") int year, @Param("user") User user);
}
