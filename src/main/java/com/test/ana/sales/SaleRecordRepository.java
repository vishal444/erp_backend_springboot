package com.test.ana.sales;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaleRecordRepository extends JpaRepository<SaleRecord, Long> {
    @Query( "SELECT sr FROM SaleRecord sr WHERE sr.sales.id = :salesId AND sr.product.product_id = :productId")
    SaleRecord getSaleRecord(@Param("salesId") Long salesId, @Param("productId") Integer productId);

//    @Query("SELECT p.name, SUM(sr.quantity) FROM SaleRecord sr JOIN sr.product p GROUP BY p.id")
//    List<Object[]> getTotalQuantitiesGraph();
    @Query("SELECT p.name, SUM(sr.current_quantity) FROM SaleRecord sr JOIN sr.product p WHERE sr.user = :user GROUP BY p.id, p.name")
    List<Object[]> getTotalQuantitiesGraph(@Param("user") User user);


    @Query("SELECT SUM(s.current_quantity), MONTH(s.date), YEAR(s.date) FROM SaleRecord s WHERE s.product.id = :productId AND s.user = :user GROUP BY DAY(s.date), MONTH(s.date), YEAR(s.date)")
    List<Object[]> getGraphByProduct(@Param("productId") Integer productId,@Param("user") User user);

//    @Query("SELECT SUM(s.quantity), DATE_FORMAT(s.date, '%M') FROM SaleRecord s WHERE s.product.id = :productId GROUP BY MONTH(s.date)")
//    List<Object[]> getGraphByProduct1(@Param("productId") Integer productId);
}
