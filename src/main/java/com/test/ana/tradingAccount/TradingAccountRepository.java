package com.test.ana.tradingAccount;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingAccountRepository extends JpaRepository<TradingAccount, Long> {
    @Query("SELECT ta FROM TradingAccount ta WHERE ta.year = :year AND ta.user = :user")
    TradingAccount findByYear(@Param("year") int year, @Param("user") User user);
}
