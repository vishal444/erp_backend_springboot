package com.test.ana.salary;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Integer> {
    @Query("SELECT SUM(s.salaryReceived) FROM Salary s WHERE YEAR(s.date) = :year AND s.user = :user")
    Double getNetSalaryPaid(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(s.salaryExtra) FROM Salary s WHERE YEAR(s.date) = :year AND s.user = :user")
    Double getTotalSalaryExtra(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(s.salaryOutstanding) FROM Salary s WHERE YEAR(s.date) = :year AND s.user = :user")
    Double getTotalOutstandingSalary(@Param("year") int year, @Param("user") User user);
}
