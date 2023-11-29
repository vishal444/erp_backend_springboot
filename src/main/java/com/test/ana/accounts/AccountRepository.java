package com.test.ana.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, Integer> {
    @Query("SELECT ad.description  FROM AccountData ad")
    List<String> getAccountDescription();
}

