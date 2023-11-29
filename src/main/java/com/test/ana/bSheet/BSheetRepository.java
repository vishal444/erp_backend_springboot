package com.test.ana.bSheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BSheetRepository extends JpaRepository<BSheet, Long> {
}
