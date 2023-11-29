package com.test.ana.bSheet;

import com.test.ana.assets.AssetRepository;
import com.test.ana.loans.LoanPaymentRepository;
import com.test.ana.loans.LoanRepository;
import com.test.ana.overDraft.ODPaymentRepository;
import com.test.ana.overDraft.ODRepository;
import com.test.ana.pandLAccount.ProfitLossAccount;
import com.test.ana.pandLAccount.ProfitLossAcntRepository;
import com.test.ana.pandLAccount.ProfitLossAcntService;
import com.test.ana.rent.RentPaymentRepository;
import com.test.ana.salary.SalaryRepository;
import com.test.ana.sales.SalesRepository;
import com.test.ana.tradingAccount.TradingAccountService;
import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BSheetService {
    @Autowired
    private final BSheetRepository bSheetRepository;
    @Autowired
    private final ProfitLossAcntRepository profitLossAcntRepository;
    @Autowired
    private final TradingAccountService tradingAccountService;
    @Autowired
    private final ProfitLossAcntService profitLossAcntService;
    @Autowired
    private final SalaryRepository salaryRepository;
    @Autowired
    private final RentPaymentRepository rentPaymentRepository;
    @Autowired
    private final LoanPaymentRepository loanPaymentRepository;
    @Autowired
    private final LoanRepository loanRepository;
    @Autowired
    private final SalesRepository salesRepository;
    @Autowired
    private final AssetRepository assetRepository;
    @Autowired
    private final ODRepository odRepository;
    @Autowired
    private final ODPaymentRepository odPaymentRepository;
    @Autowired
    private final UserRepository userRepository;

    public void generateBalanceSheet(int year, String userName) {
        tradingAccountService.generateTradingAccount(year, userName);
        profitLossAcntService.generatePandLAccount(year,userName);
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        // loan, rent and salary extra paid money
        double totalExtraPaidSalary = salaryRepository.getTotalSalaryExtra(year, user);
        double totalExtraPaidRent = rentPaymentRepository.getTotalRentPaidExtra(year, user);
//        double totalExtraPaidLoan = loanPaymentRepository.getLoanPaidExtra(year);
        double prePaidExpenses = totalExtraPaidRent + totalExtraPaidSalary;

        // outstanding expenses ie total money yet to be paid
        double totalOutstandingSalary = salaryRepository.getTotalOutstandingSalary(year, user);
        double totalOutstandingRent = rentPaymentRepository.getTotalRentOutstanding(year, user);
//        double totalOutstandingLoans = loanPaymentRepository.getTotalLoanOutstanding(year);
        double outstandingExpenses = totalOutstandingSalary + totalOutstandingRent ;

        // outstanding incomes ie pattu paisa varanullathu
        double outstandingIncomes = salesRepository.getTotalOutstandingSalesIncome(year, user);

        double loanAmount = loanRepository.getTotalSumLoans(user) - loanPaymentRepository.getTotalPaidLoanAmount(user);
        double overDraftAmount = odRepository.getTotalODWithdrawn(year, user) - odPaymentRepository.getTotalODRepaid(year, user);

        double capital = assetRepository.getSumCapital(user);
        ProfitLossAccount pLAccount = profitLossAcntRepository.findByYear(year, user);
        double pLNetLoss = pLAccount.getNetLoss();
        double pLNetProfit = pLAccount.getNetProfit();
        capital = capital + pLNetProfit - pLNetLoss;
        double furniture = assetRepository.getFurniture(user);
        double machinery = assetRepository.getMachinery(user);
        double land = assetRepository.getLand(user);
        double building = assetRepository.getBuilding(user);
        double equipments = assetRepository.getEquipments(user);
        double motorVehicles = assetRepository.getMotorVehicles(user);
        double bankDeposits = assetRepository.getBankDeposits(user);
        double investments = assetRepository.getInvestments(user);
        // closing stock ??
        BSheet bSheet = new BSheet();
        bSheet.setCapital(capital);
        bSheet.setPrepaidExpenses(prePaidExpenses);
        bSheet.setOutstandingExpenses(outstandingExpenses);
        bSheet.setOutstandingIncomes(outstandingIncomes);
        bSheet.setLoans(loanAmount);
        bSheet.setOverDrafts(overDraftAmount);
        bSheet.setFurniture(furniture);
        bSheet.setMachinery(machinery);
        bSheet.setLand(land);
        bSheet.setBuilding(building);
        bSheet.setEquipments(equipments);
        bSheet.setMotorVehicles(motorVehicles);
        bSheet.setCashAtBank(bankDeposits);         //  ???
        bSheet.setInvestments(investments);
        bSheet.setUser(user);
        bSheetRepository.save(bSheet);
    }
}
