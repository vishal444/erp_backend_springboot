package com.test.ana.tradingAccount;

import com.test.ana.inventory.InventoryRepository;
import com.test.ana.purchases.PurchaseRepository;
import com.test.ana.sales.SalesRepository;
import com.test.ana.stockBalance.StockBalance;
import com.test.ana.stockBalance.StockBalanceRepository;
import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TradingAccountService {
    @Autowired
    private final TradingAccountRepository tradingAccountRepository;
    @Autowired
    private final InventoryRepository inventoryRepository;
    @Autowired
    private final StockBalanceRepository stockBalanceRepository;
    @Autowired
    private final SalesRepository salesRepository;
    @Autowired
    private final PurchaseRepository purchaseRepository;
    @Autowired
    private final UserRepository userRepository;
    @Transactional
    public void addFirstOpeningStock(Integer year, String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        StockBalance stockBalance = stockBalanceRepository.getOpeningStockByYearAndUser(year, user);
        if(stockBalance != null){
            return;
        }
        Double openingStockForFirstYear = inventoryRepository.getStock(user);
        System.out.println("OPENING STOCK::::::::::::::::::::::::::::::::: "+openingStockForFirstYear);
        StockBalance sBal = new StockBalance();
        sBal.setId(1L);
        sBal.setOpeningStock(openingStockForFirstYear);
        sBal.setYear(year);
        sBal.setUser(user);
        stockBalanceRepository.save(sBal);
    }

    //    @Scheduled(cron = "0 0 0 1 1 *") // run at midnight on January 1st every year
    @Transactional
    public void generateTradingAccount(int year, String userName) {
        double netPurchases = 0.00;
        double netPurchaseReturns = 0.00;
        double purchasesAfter = 0.00;
        double netSales = 0.00;
        double netSalesReturns = 0.00;
        double salesAfter = 0.00;
        double endingInventory = 0.00;
        double directExpenses = 0.00;
        double cogs = 0.00;
        double openingStock = 0.00;
        double closingStock = 0.00;
        double credit_side = 0.00;
        double debit_side = 0.00;
        double result = 0.00;
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        // get the opening stock from the stock balance entity for the first year
        StockBalance stockBalance = stockBalanceRepository.getOpeningStockByYearAndUser(year, user);
        if(stockBalance!=null){
            openingStock = stockBalance.getOpeningStock();
        }

        // get the opening stock for the second year from the closing stock of trading account entity from the previous year
        TradingAccount previousYearTradingAccount = tradingAccountRepository.findByYear(year - 1,user);
        if(previousYearTradingAccount!=null){
            openingStock = previousYearTradingAccount.getClosingStock();
        }

        // calculate the net purchases and net sales for the year
        netPurchases = purchaseRepository.getNetPurchaseAmount(user);// calculate net purchases for the year
        netPurchaseReturns = purchaseRepository.getNetRefundedPurchaseAmount(user);    // need to handle nullpointerexception
        purchasesAfter = netPurchases - netPurchaseReturns;
        netSales = salesRepository.getNetSalesAmount(user);// calculate net sales for the year
        netSalesReturns = salesRepository.getNetReturnedSalesAmount(user);
        salesAfter = netSales - netSalesReturns;
        endingInventory = inventoryRepository.getStock(user);

        cogs = openingStock + purchasesAfter - endingInventory;
        // calculate the closing stock for the year
        closingStock = openingStock + purchasesAfter - cogs;
        credit_side = salesAfter + closingStock;
        debit_side = openingStock + purchasesAfter - directExpenses;    // TODO direct expense + or -
        result = credit_side - debit_side;
        double grossProfit = 0.00;
        double grossLoss = 0.00;
        if(result > 0){
            grossProfit = result;
        } else {
            grossLoss =  Math.abs(result);
        }

        // create a new trading account entity for the year
        TradingAccount tradingAccount = new TradingAccount();
        tradingAccount.setYear(year);
        tradingAccount.setOpeningStock(openingStock);
        tradingAccount.setNetPurchase(netPurchases);
        tradingAccount.setNetPurchaseReturns(netPurchaseReturns);
        tradingAccount.setNetSales(netSales);
        tradingAccount.setNetSalesReturns(netSalesReturns);
        tradingAccount.setClosingStock(closingStock);
        tradingAccount.setDirectExpenses(directExpenses);
        tradingAccount.setGrossProfit(grossProfit);
        tradingAccount.setGrossLoss(grossLoss);
        tradingAccount.setUser(user);
        // save the trading account entity to the database
        tradingAccountRepository.save(tradingAccount);
    }
}