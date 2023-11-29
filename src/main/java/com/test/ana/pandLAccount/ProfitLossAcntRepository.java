package com.test.ana.pandLAccount;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitLossAcntRepository extends JpaRepository<ProfitLossAccount, Long> {
    @Query("SELECT pl FROM ProfitLossAccount pl WHERE pl.year = :year AND pl.user = :user ")
    ProfitLossAccount findByYear(@Param("year")Integer year,@Param("user") User user);
}
