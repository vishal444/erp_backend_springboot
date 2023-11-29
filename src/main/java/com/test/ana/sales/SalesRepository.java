package com.test.ana.sales;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
//    @Query("SELECT s FROM Sales s WHERE s.product_id = :product_id AND s.sales_date BETWEEN :startDate AND :endDate")
//    List<Sales> findSalesByProductIdAndSaleDateBetween(@Param("product_id") Product product_id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(s.grandTotal) FROM Sales s WHERE s.user = :user")
    Double getNetSalesAmount(@Param("user") User user);

    @Query("SELECT SUM(s.returnedTotalAmount) FROM Sales s WHERE s.user = :user")
    Double getNetReturnedSalesAmount(@Param("user") User user);

    @Query("SELECT s FROM Sales s")
    List<Sales> getAllSales();

    @Query("SELECT s FROM Sales s WHERE s.outstandingAmount <> 0 AND s.outstandingAmount >= 0 AND s.user = :user")
    List<Sales> getAllOutstandingSales(@Param("user") User user);


    @Query("SELECT SUM(s.outstandingAmount) FROM Sales s WHERE YEAR(s.date) = :year  AND s.user = :user")
    Double getTotalOutstandingSalesIncome(@Param("year") int year, @Param("user") User user);

    @Query("SELECT s FROM Sales s WHERE s.user = :user")
    List<Sales> findAllSalesByUser(@Param("user") User user);
    @Query("SELECT s FROM Sales s WHERE s.id = :salesId AND s.user = :user")
    Sales findByIdAndUser(@Param("salesId") Long id,@Param("user") User user);
}
