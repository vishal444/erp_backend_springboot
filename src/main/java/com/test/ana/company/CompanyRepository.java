package com.test.ana.company;

import com.test.ana.assets.Assets;
import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query("SELECT c FROM Company c WHERE c.user = :user")
    Company findByUser(@Param("user") User user);
}
