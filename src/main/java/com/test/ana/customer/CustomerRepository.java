package com.test.ana.customer;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer>  findCustomerByEmail(String email);
    @Query("SELECT c FROM Customer c WHERE c.user = :user")
    List<Customer> findAllCustomerByUser(@Param("user") User user);
}
