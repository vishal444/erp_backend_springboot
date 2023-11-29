package com.test.ana.monthlyIncomeExpenses;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IandERepository extends JpaRepository<IncomeAndExpenses, Long> {
    @Query("SELECT SUM(i.electricity) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalPower(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.internet) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalInternet(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.insurance) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalInsurance(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.taxes) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalTaxes(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.transport) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalTransport(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.cookingGas) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalCookingGas(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.audit) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalAudit(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.repairAndMaintenance) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalRepairMaintenance(@Param("year") int year, @Param("user") User user);

    @Query("SELECT SUM(i.deliveryCharges) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalDeliveryCharges(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.damageOfStock) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalDamageOfStock(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.lossSaleFixedAsset) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalLossSaleFixedAsset(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.badDebtWrittenOff) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalBadDebtWrittenOff(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.miscellaneousExpenses) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalMiscellaneousExpense(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.interestIncomeLent) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalInterestIncomeLent(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.rentReceived) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalRentReceived(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.discountReceived) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalDiscountReceived(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.badDebtsRecovered) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalBadDebtsRecovered(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.profitSaleFixedAsset) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalProfitSaleFixedAsset(@Param("year") int year, @Param("user") User user);
    @Query("SELECT SUM(i.miscellaneousIncomes) FROM IncomeAndExpenses i WHERE YEAR(i.date) = :year AND i.user = :user")
    Double getTotalMiscellaneousIncomes(@Param("year") int year, @Param("user") User user);
}
