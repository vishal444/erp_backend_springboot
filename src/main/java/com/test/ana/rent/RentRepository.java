package com.test.ana.rent;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    @Query("SELECT r FROM Rent r WHERE r.user = :user")
    List<Rent> findRentsByUser(@Param("user") User user);
}
