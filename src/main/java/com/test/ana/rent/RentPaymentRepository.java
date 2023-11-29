package com.test.ana.rent;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentPaymentRepository extends JpaRepository<RentPayment, Long> {
    @Query("SELECT SUM(rp.paidRent) FROM RentPayment rp WHERE YEAR(rp.date) = :year AND rp.user = :user")
    Double getNetRentPaid(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(rp.rentExtra) FROM RentPayment rp WHERE YEAR(rp.date) = :year AND rp.user = :user")
    Double getTotalRentPaidExtra(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(rp.rentOutstanding) FROM RentPayment rp WHERE YEAR(rp.date) = :year AND rp.user = :user")
    Double getTotalRentOutstanding(@Param("year") int year, @Param("user") User user);
}
