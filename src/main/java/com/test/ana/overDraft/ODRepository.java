package com.test.ana.overDraft;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ODRepository extends JpaRepository<OverDraft, Long> {
    @Query("SELECT od.withdrawnAmount FROM OverDraft od")
    Double getTotalODWithdrawnAmount();

    @Query("SELECT SUM(od.withdrawnAmount) FROM OverDraft od WHERE YEAR(od.date) = :year AND od.user = :user")
    Double getTotalODWithdrawn(@Param("year") int year, @Param("user") User user);
}
