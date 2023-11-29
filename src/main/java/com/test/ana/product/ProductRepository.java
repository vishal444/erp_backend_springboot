package com.test.ana.product;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findProductByName(String name);
    @Query("SELECT p FROM Product p WHERE p.user = :user")
    List<Product> findAllProductByUser(@Param("user") User user);
    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.user = :user")
    Product  findByIdAndUser(Integer id, User user);
}
