package com.test.ana.assets;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Assets, Integer> {
    @Query("SELECT  SUM(capital) FROM Assets a WHERE a.user = :user")
    double getSumCapital(@Param("user") User user);

    @Query("SELECT  furniture FROM Assets a WHERE a.user = :user")
    double getFurniture(@Param("user") User user);

    @Query("SELECT  machinery FROM Assets a WHERE a.user = :user")
    double getMachinery(@Param("user") User user);

    @Query("SELECT  land FROM Assets a WHERE a.user = :user")
    double getLand(@Param("user") User user);

    @Query("SELECT  building FROM Assets a WHERE a.user = :user")
    double getBuilding(@Param("user") User user);

    @Query("SELECT  equipments FROM Assets a WHERE a.user = :user")
    double getEquipments(@Param("user") User user);

    @Query("SELECT  motorVehicles FROM Assets a WHERE a.user = :user")
    double getMotorVehicles(@Param("user") User user);

    @Query("SELECT  bankDeposits FROM Assets a WHERE a.user = :user")
    double getBankDeposits(@Param("user") User user);

    @Query("SELECT  investments FROM Assets a WHERE a.user = :user")
    double getInvestments(@Param("user") User user);
//    @Query("SELECT a FROM Assets a WHERE a.user = :user")
//    Assets findByUserId(@Param("user") User user);

    @Query("SELECT a FROM Assets a WHERE a.user = :user")
    Assets getAssetByUser(@Param("user") User user);
//    @Query("SELECT a FROM Assets a WHERE a.user = :user AND a.columnName = :colName")
//    Assets getAssetByUserAndColumnName(@Param("colName") String columnName, @Param("user") User user);
}
