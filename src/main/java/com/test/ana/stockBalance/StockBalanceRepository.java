package com.test.ana.stockBalance;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockBalanceRepository extends JpaRepository<StockBalance, Long> {
    @Query("SELECT sb FROM StockBalance sb WHERE sb.year = :year AND sb.user =:user")
    StockBalance getOpeningStockByYearAndUser(@Param("year") int year, @Param("user") User user);

    @Query("SELECT sb FROM StockBalance sb WHERE sb.user =:user")
    StockBalance getOpeningStockByUser( @Param("user") User user);
}
