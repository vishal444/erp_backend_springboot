package com.test.ana;

import com.test.ana.accounts.AccountData;
import com.test.ana.accounts.AccountService;
import com.test.ana.assets.AssetService;
import com.test.ana.assets.Assets;
import com.test.ana.bSheet.BSheetService;
import com.test.ana.company.Company;
import com.test.ana.company.CompanyService;
import com.test.ana.customer.Customer;
import com.test.ana.customer.CustomerService;
import com.test.ana.employees.Employee;
import com.test.ana.employees.EmployeeService;
import com.test.ana.inventory.Inventory;
import com.test.ana.inventory.InventoryService;
import com.test.ana.loans.Loan;
import com.test.ana.loans.LoanPayment;
import com.test.ana.loans.LoanService;
import com.test.ana.monthlyIncomeExpenses.IandEService;
import com.test.ana.monthlyIncomeExpenses.IncomeAndExpenses;
import com.test.ana.overDraft.ODPayment;
import com.test.ana.overDraft.ODService;
import com.test.ana.overDraft.OverDraft;
import com.test.ana.pandLAccount.ProfitLossAcntService;
import com.test.ana.product.Product;
import com.test.ana.product.ProductDTO;
import com.test.ana.product.ProductService;
import com.test.ana.purchases.PurchaseDTO;
import com.test.ana.purchases.PurchaseService;
import com.test.ana.purchases.Purchase;
import com.test.ana.rent.Rent;
import com.test.ana.rent.RentPayment;
import com.test.ana.rent.RentService;
import com.test.ana.salary.Salary;
import com.test.ana.salary.SalaryService;
import com.test.ana.sales.*;
import com.test.ana.stockBalance.StockBalance;
import com.test.ana.stockBalance.StockBalanceService;
import com.test.ana.tradingAccount.TradingAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/erp/")
@CrossOrigin(origins = "https://bisbuddy.online")
@AllArgsConstructor
public class ERPContoller {
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final SalesService salesService;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final SalaryService salaryService;
    @Autowired
    private final InventoryService inventoryService;
    @Autowired
    private final PurchaseService purchaseService;
    @Autowired
    private final TradingAccountService tradingAccountService;
    @Autowired
    private final ProfitLossAcntService profitLossAcntService;
    @Autowired
    private final LoanService loanService;
    @Autowired
    private final RentService rentService;
    @Autowired
    private final ODService odService;
    @Autowired
    private final IandEService iandEService;
    @Autowired
    private final CompanyService companyService;
    @Autowired
    private final AssetService assetService;
    @Autowired
    private final BSheetService bSheetService;
    @Autowired
    private final StockBalanceService stockBalanceService;

    // @GetMapping("/customer")
    // public Customer getCustomer(){
    // return customerService.getCustomer();
    // }
    @GetMapping("/health")
    public String healthCheck() {
        return "Health Check Test !!";
    }
    @PostMapping("/company/save")
    public void saveCompanyData(@RequestBody Company company){
        companyService.saveCompanyData(company);
    }
    @GetMapping("/company/{userName}")
    public Company getCompanyData(@PathVariable("userName") String userName) {
        return companyService.getCompanyData(userName);
    }
    @PutMapping("/company/edit/{columnName}/{userName}")
    public void updateCompany(@PathVariable("columnName") String columnName,
                              @PathVariable("userName") String userName,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String address,
                              @RequestParam(required = false) Double zip,
                              @RequestParam(required = false) Double phone,
                              @RequestParam(required = false) Double gstN)
                             {
        companyService.updateCompany(columnName, userName,name, address, zip, phone, gstN);
    }
    @PostMapping("/asset/save")
    public void saveAssetData(@RequestBody Assets asset){
        assetService.saveAssetData(asset);
    }
    @GetMapping("/assets/{userName}")
    public Assets getAllAsset(@PathVariable("userName") String userName) {
        return assetService.getAllAssets(userName);
    }
    @PutMapping("/asset/edit/{columnName}/{newValue}/{userName}")
    public void updateAsset(@PathVariable("columnName") String columnName,
                            @PathVariable("gstN") double newValue,
                            @PathVariable("userName") String userName) {
        assetService.updateAsset(columnName, newValue, userName);
    }
    @GetMapping("/customer/getAll/{userName}")
    public List<Customer> getAllCustomers(@PathVariable("userName") String userName) {
        return customerService.getAllCustomers(userName);
    }

    @PostMapping("/customer/add")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    // PRODUCT
    @GetMapping("/product/get/{productId}")
    public Product getProduct(@PathVariable("productId") Integer id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product/getAll/{userName}")
    public List<Product> getAllProducts(@PathVariable("userName") String userName) {
        return productService.getAllProducts(userName);
    }

    @PostMapping("/product/add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PostMapping("/productList/add")
    public void addListOfProducts(@RequestBody List<ProductDTO> productDTO) {
            productService.addListOfProducts(productDTO);
    }
    @DeleteMapping(path = "/product/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Integer id) {
        productService.deleteProduct(id);
    }

    @PutMapping(path = "/product/update/{productId}")
    public void editProductDetails(
            @PathVariable("productId") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        productService.editProductDetail(id, name, description);
    }
    @PutMapping(path = "/productUpdate/{productId}/{userName}")
    public void editProduct(
            @PathVariable("productId") Integer id,
            @PathVariable("userName") String userName,
            @RequestParam(required = false)  String name,
            @RequestParam(required = false) String gst,
            @RequestParam(required = false) Double price
            ) {
        productService.editProduct(id,  userName, name, gst, price);
    }
    // SALES
    @GetMapping("/sales/{userName}")
    public List<Sales> getAllSales(@PathVariable("userName") String userName) {
        return salesService.getAllSales(userName);
    }

    @GetMapping("/salesById/{sales_id}/{userName}")
    public Sales getSalesProductsDetailById(@PathVariable("sales_id") Long sales_id,
                                            @PathVariable("userName") String userName) {
        return salesService.getById(sales_id,userName);
    }

    @GetMapping("/salesOutstanding/{userName}")
    public List<Sales> getAllOutstandingSales(@PathVariable("userName") String userName) {
        return salesService.getAllOutstandingSales(userName);
    }

    @GetMapping("/sales/graphByProduct/{productId}/{userName}")
    public List<Object[]> getGraphByProduct(
            @PathVariable("productId") Integer id,
            @PathVariable("userName") String userName) {
        return salesService.getGraphByProduct(id, userName);
    }

    @GetMapping("/sales/graphAll/{userName}")
    public List<Object[]> getGraphAllSales(@PathVariable("userName") String userName) {
        return salesService.getGraphAllSales(userName);
    }

    @PutMapping("/sales/return/{sales_id}/{product_id}/{userName}")
    public void updateSalesReturn(@PathVariable("sales_id") Long sales_id,
            @PathVariable("product_id") Integer product_id,
                                  @PathVariable("userName") String userName,
            @RequestParam("returnQuantity") Double returnQuantity,
            @RequestParam("returnedAmount") Double returnedAmount) {
        salesService.updateSalesReturn(sales_id, product_id, userName, returnQuantity, returnedAmount);
    }

    @PutMapping("/sales/advance/{sales_id}/{userName}")
    public void updateSalesAdvance(@PathVariable("sales_id") Long sales_id,
                                   @PathVariable("userName") String userName,
            @RequestParam("nextAdvance") Double nextAdvance) {
        salesService.updateSalesAdvance(sales_id, userName, nextAdvance);
    }

    @PostMapping("/sales/add")
    public void saveSaleWithItems(@RequestBody SaleDTO saleDTO) {
        salesService.addSales(saleDTO);
    }

    // Purchases
    @PostMapping("/purchases/add")
    public void addPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        purchaseService.addPurchase(purchaseDTO);
    }

    @GetMapping("/purchases/{userName}")
    public List<Purchase> getAllPurchases(@PathVariable("userName") String userName) {
        return purchaseService.getAllPurchase(userName);
    }
    @PutMapping("purchases/partPayment/{purchases_id}/{userName}")
    public void updatePurchasesPartPayment(@PathVariable("purchases_id") Long purchases_id,
                                           @PathVariable("userName") String userName,
                                   @RequestParam("nextAdvance") Double nextPartPayment) {
        purchaseService.updatePurchasesPartPayment(purchases_id, nextPartPayment, userName);
    }
    @GetMapping("/purchasesById/{purchase_id}/{userName}")
    public Purchase getPurchaseProductsDetailById(@PathVariable("purchase_id") Long purchase_id,
                                                   @PathVariable("userName") String userName) {
        return purchaseService.getByIdAndUser(purchase_id, userName);
    }

    @PutMapping("/purchase/return/{purchase_id}/{product_id}/{userName}")
    public void updatePurchaseReturn(
            @PathVariable("purchase_id") Long purchase_id,
            @PathVariable("product_id") Integer product_id,
            @PathVariable("userName") String userName,
            @RequestParam("returnQuantity") Double returnQuantity,
            @RequestParam("returnedAmount") Double returnedAmount) {
        purchaseService.updatePurchaseReturn(purchase_id, product_id, userName, returnQuantity, returnedAmount);
    }
    @GetMapping("/purchaseOutstanding/{userName}")
    public List<Purchase> getAllOutstandingPurchases(@PathVariable("userName") String userName) {
        return purchaseService.getAllOutstandingPurchases(userName);
    }

    // Accounts
    @GetMapping("/getBankExcelData")
    public AccountData getBalanceSheet() {
        return accountService.getBalanceSheet();
    }

    @PostMapping("/accountData")
    public void addAccountData(@RequestBody List<AccountData> data) {
        accountService.addAccountData(data);
    }

    // Staff
    @PostMapping("/employee/add")
    public void addStaff(@RequestBody Employee staff) {
        employeeService.addStaff(staff);
    }

    @GetMapping("/employees/{userName}")
    public List<Employee> getStaff(@PathVariable("userName") String userName) {
        return employeeService.getStaff(userName);
    }

    @GetMapping("/employeeById/{employee_id}")
    public Employee getEmployeeById(
            @PathVariable("employee_id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/salaryPayment/save")
    public void saveSalaryRecord(@RequestBody Salary record) {
        salaryService.saveSalaryRecord(record);
    }

    // LOANS
    @PostMapping("/loan/add")
    public void saveLoanData(@RequestBody Loan record) {
        loanService.saveLoanRecord(record);
    }

    @GetMapping("/loans/{userName}")
    public List<Loan> getLoan(@PathVariable("userName") String userName) {
        return loanService.getLoans(userName);
    }

    @GetMapping("/loanById/{loan_id}")
    public Loan getLoanById(@PathVariable("loan_id") Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping("/loanPayment/save")
    public void saveLoanPayment(@RequestBody LoanPayment record) {
        loanService.saveLoanPayment(record);
    }

    // RENT
    @PostMapping("/rent/add")
    public void saveRentData(@RequestBody Rent record) {
        rentService.saveRentRecord(record);
    }

    @PostMapping("/rentPayment/save")
    public void saveRentPayment(@RequestBody RentPayment record) {
        rentService.saveRentPayment(record);
    }

    @GetMapping("/rent/{userName}")
    public List<Rent> getRentData(@PathVariable("userName") String userName) {
        return rentService.getRents(userName);
    }

    @GetMapping("/rentById/{rent_id}")
    public Rent getRentById(@PathVariable("rent_id") Long id) {
        return rentService.getRentById(id);
    }

    @GetMapping("/rentPayment")
    public List<RentPayment> getRentPayment() {
        return rentService.getRentPayment();
    }

    // INVENTORY
    @PutMapping("/inventory/purchase/update/{product_id}/{userName}")
    public void updatePurchaseInventory(@PathVariable("product_id") Product product_id,
                                        @PathVariable("userName") String userName,
            @RequestParam("quantity") Double quantity,
            @RequestParam("purchase_price") Double price) {
        inventoryService.updatePurchaseInventory(product_id, userName, quantity, price);
    }

    @PutMapping("/inventory/purchase/return/{product_id}/{userName}")
    public void updatePurchaseReturn(@PathVariable("product_id") Product product_id,
                                     @PathVariable("userName") String userName,
            @RequestParam("quantity") Double quantity) {
        inventoryService.updatePurchaseReturn(product_id, userName, quantity);
    }

    @PutMapping("/inventory/sale/return/{product_id}/{userName}")
    public void updateSaleReturn(@PathVariable("product_id") Product product_id,
                                 @PathVariable("userName") String userName,
            @RequestParam("quantity") Double quantity) {
        inventoryService.updateSaleReturn(product_id, userName, quantity);
    }

    @PutMapping("/inventory/sale/update/{product_id}/{userName}")
    public void updateSaleInventory(@PathVariable("product_id") Product product_id,
                                    @PathVariable("userName") String userName,
            @RequestParam("quantity") Double quantity) {
        inventoryService.updateSaleInventory(product_id, userName, quantity);
    }

    @PutMapping("/inventory/update/{product_id}")
    public void updateInventory(@PathVariable("product_id") Product product_id,
            @RequestParam("quantity") Double quantity,
            @RequestParam("buyingPrice") Double buying_price) {
        inventoryService.updateInventory(product_id, quantity, buying_price);
    }

    @GetMapping("/inventory/getAll/{userName}")
    public List<Inventory> getAllInventory(@PathVariable("userName") String userName) {
        return inventoryService.getAllInventory(userName);
    }

    // TRADING ACCOUNT
    @PutMapping("/tAC/addOpeningStock/{year}/{userName}")
    public void addOpeningStock(@PathVariable("year") Integer year,
                                @PathVariable("userName") String userName) {
        tradingAccountService.addFirstOpeningStock(year,userName);
    }
    @GetMapping("/openingStock/{userName}")
    public StockBalance getOpeningStock(@PathVariable("userName") String userName) {
        return stockBalanceService.getOpeningStock(userName);
    }
    @PutMapping("/tAC/generate/{year}/{userName}")
    public void generateTradingAccount(@PathVariable("year") int year,
                                       @PathVariable("userName") String userName) {
        tradingAccountService.generateTradingAccount(year,userName);
    }

    // OVER DRAFT
    @PostMapping("/OD/add")
    public void saveODAmount(@RequestBody OverDraft overDraft) {
        odService.saveODAmount(overDraft);
    }

    @PostMapping("/overDraftPayment/save")
    public void saveODPayment(@RequestBody ODPayment odPayment) {
        odService.saveODPayment(odPayment);
    }
    // Capital Draw
    @PutMapping("/capitalDraw/{capitalDraw}/{userName}")
    public void updateCapital(@PathVariable("capitalDraw") double  amount,
                              @PathVariable("userName") String userName) {
        assetService.updateCapital(amount, userName);
    }
    // PandL Account
    @PutMapping("/pAndL/generate/{year}/{userName}")
    public void generatePandLAccount(@PathVariable("year") int year,
                                     @PathVariable("userName") String userName) {
        profitLossAcntService.generatePandLAccount(year, userName);
    }

    @PostMapping("/monthlyIncomeExpense/save")
    public void saveIncomeExpense(@RequestBody IncomeAndExpenses record) {
        iandEService.saveIncomeExpense(record);
    }

    // BALANCE SHEET
    @PutMapping("/balanceSheet/generate/{year}/{userName}")
    public void generateBalanceSheet(@PathVariable("year") int year,
                                     @PathVariable("userName") String userName) {
        bSheetService.generateBalanceSheet(year, userName);
    }
}
