package com.test.ana.pandLAccount;


import com.test.ana.accounts.AccountRepository;
import com.test.ana.loans.LoanPaymentRepository;
import com.test.ana.monthlyIncomeExpenses.IandERepository;
import com.test.ana.rent.RentPaymentRepository;
import com.test.ana.salary.SalaryRepository;
import com.test.ana.tradingAccount.TradingAccount;
import com.test.ana.tradingAccount.TradingAccountRepository;
import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProfitLossAcntService {
    @Autowired
    private final ProfitLossAcntRepository profitLossAcntRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final TradingAccountRepository tradingAccountRepository;
    @Autowired
    private final IandERepository iandERepository;
    @Autowired
    private final SalaryRepository salaryRepository;
    @Autowired
    private final RentPaymentRepository rentPaymentRepository;
    @Autowired
    private final LoanPaymentRepository loanPaymentRepository;
    @Autowired
    private final UserRepository userRepository;
    public void generatePandLAccount(int year, String userName){
        double creditSide = 0.00;
        double debitSide = 0.00;

        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        TradingAccount tAC = tradingAccountRepository.findByYear(year, user);
        double grossProfitTAC = tAC.getGrossProfit();          // get gross profit from trading account
        double grossLossTAC = tAC.getGrossLoss();              // get gross loss from trading account

        double powerCharges = iandERepository.getTotalPower(year, user);
        double internet = iandERepository.getTotalInternet(year, user);
        double insurance = iandERepository.getTotalInsurance(year, user);
        double tax = iandERepository.getTotalTaxes(year, user);
        double transport = iandERepository.getTotalTransport(year, user);
        double cookingGas = iandERepository.getTotalCookingGas(year, user);
        double audit = iandERepository.getTotalAudit(year, user);
        double repairMaintenance = iandERepository.getTotalRepairMaintenance(year, user);
        double deliveryCharges = iandERepository.getTotalDeliveryCharges(year, user);
        double damageOfStock = iandERepository.getTotalDamageOfStock(year, user);
        double lossSaleFixedAsset = iandERepository.getTotalLossSaleFixedAsset(year, user);
        double badDebtWrittenOff = iandERepository.getTotalBadDebtWrittenOff(year,user);
        double miscellaneousExpense = iandERepository.getTotalMiscellaneousExpense(year, user);
        double interestIncomeLent = iandERepository.getTotalInterestIncomeLent(year, user);
        double rentReceived  = iandERepository.getTotalRentReceived(year, user);
        double discountReceived = iandERepository.getTotalDiscountReceived(year, user);
        double badDebtsRecovered = iandERepository.getTotalBadDebtsRecovered(year, user);
        double profitSaleFixedAsset = iandERepository.getTotalProfitSaleFixedAsset(year, user);
        double miscellaneousIncomes = iandERepository.getTotalMiscellaneousIncomes(year, user);

        /// after adjusting Prepaid expenses
        double salary = salaryRepository.getNetSalaryPaid(year, user)- salaryRepository.getTotalSalaryExtra(year, user);// -
        double loan = loanPaymentRepository.getNetLoanPaid(year, user);
        double rent = rentPaymentRepository.getNetRentPaid(year, user) - rentPaymentRepository.getTotalRentPaidExtra(year, user);
        debitSide = grossLossTAC + salary + loan + rent + powerCharges + internet + insurance + tax + transport + cookingGas + audit + repairMaintenance + deliveryCharges + damageOfStock + lossSaleFixedAsset + badDebtWrittenOff + miscellaneousExpense;
        creditSide = grossProfitTAC + interestIncomeLent + rentReceived + discountReceived + badDebtsRecovered + profitSaleFixedAsset + miscellaneousIncomes;
        double result = creditSide - debitSide;
        double netProfit = 0.00;
        double netLoss = 0.00;
        if(result > 0){
            netProfit = result;
        } else {
            netLoss =  Math.abs(result);
        }
        ProfitLossAccount pandLAcnt = new ProfitLossAccount();
        pandLAcnt.setSalaryPayment(salary);
        pandLAcnt.setRentPayment(rent);
        pandLAcnt.setLoanPayment(loan);
        pandLAcnt.setElectricity(powerCharges);
        pandLAcnt.setInternet(internet);
        pandLAcnt.setInsurance(insurance);
        pandLAcnt.setTaxes(tax);
        pandLAcnt.setTransport(transport);
        pandLAcnt.setCookingGas(cookingGas);
        pandLAcnt.setAudit(audit);
        pandLAcnt.setRepairAndMaintenance(repairMaintenance);
        pandLAcnt.setDeliveryCharges(deliveryCharges);
        pandLAcnt.setDamageOfStock(damageOfStock);
        pandLAcnt.setLossOnSaleOfFixedAsset(lossSaleFixedAsset);
        pandLAcnt.setLossOnSaleOfInvestment(0);
        pandLAcnt.setBadDebtWrittenOff(badDebtWrittenOff);
        pandLAcnt.setMiscellaneousExpenses(miscellaneousExpense);
        pandLAcnt.setInterestIncomeLent(interestIncomeLent);
        pandLAcnt.setRentReceived(rentReceived);
        pandLAcnt.setDiscountReceived(discountReceived);
        pandLAcnt.setBadDebtsRecovered(badDebtsRecovered);
        pandLAcnt.setProfitOnSaleOfFixedAsset(profitSaleFixedAsset);
        pandLAcnt.setProfitOnSaleOfFixedAsset(0);
        pandLAcnt.setMiscellaneousIncomes(miscellaneousIncomes);
        pandLAcnt.setNetLoss(netLoss);
        pandLAcnt.setNetProfit(netProfit);
        pandLAcnt.setUser(user);
        profitLossAcntRepository.save(pandLAcnt);
    }
}
