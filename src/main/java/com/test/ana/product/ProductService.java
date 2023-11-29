package com.test.ana.product;

import com.test.ana.inventory.Inventory;
import com.test.ana.inventory.InventoryRepository;
import com.test.ana.stockBalance.StockBalance;
import com.test.ana.stockBalance.StockBalanceRepository;
import com.test.ana.tradingAccount.TradingAccountRepository;
import com.test.ana.tradingAccount.TradingAccountService;
import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final InventoryRepository inventoryRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    TradingAccountService tradingAccountService;
    @Autowired
    TradingAccountRepository tradingAccountRepository;
    @Autowired
    StockBalanceRepository stockBalanceRepository;

    public void addProduct(Product product) {
        Optional<Product> item = productRepository.findProductByName(product.getName());
        if (item.isPresent()) {
            throw new IllegalStateException("Product already exists");
        }
        User user = userRepository.findByEmail(product.getUserName()).orElseThrow(()->new IllegalStateException("No such user"));
        product.setUser(user);
        productRepository.save(product);

        // Generate EAN13 barcode for the new product
//        String barcode = generateEAN13(product.getProduct_id());
        String barcode = generateEAN13(String.valueOf(product.getProduct_id()));
        System.out.println("PRODUCT NUMBER IS: "+ barcode);
        // Save the barcode with the product
        product.setBarCode(barcode);
        productRepository.save(product);
        ///////////////////////////////////////
        Inventory inventory = new Inventory();
        inventory.setQuantity(0.00);
        inventory.setExpiry_date(null);
        inventory.setProduct_id(product);
        inventory.setUser(product.getUser());
        inventoryRepository.save(inventory);
    }

    public void deleteProduct(Integer id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new IllegalStateException("item dosen't exist");
        }
    }
    @Transactional
    public void editProductDetail(Integer id, String name, String description) {
        Product product = productRepository.findById(id).orElseThrow(()->new IllegalStateException("No such product"));
        if(name!= null && name.length()>0 && !Objects.equals(product.getName(),name)){
            product.setName(name);
        }
        if(description!=null && description.length()>0 && !Objects.equals(product.getDescription(),description)){
            product.setDescription(description);
        }
    }

    public Product getProduct(Integer id) {
        Product item =  productRepository.findById(id).orElseThrow(()->new IllegalStateException("no such product"));
        if(item!=null){
            return item;
        }
        return null;
    }

    public List<Product> getAllProducts(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        return productRepository.findAllProductByUser(user);
    }

    // GENERATE BAR CODES FOR EACH product
    public static String generateEAN13(String productId) {
        String barcode = "890" + String.format("%09d", Integer.parseInt(productId)); // Add Indian prefix and pad with zeros to make it 9 digits
        String checkDigit = generateCheckDigit(barcode);
        String ean13Barcode = barcode + checkDigit;
        return ean13Barcode;
    }
    private static String generateCheckDigit(String barcode) {
        int sum = 0;
        for (int i = 0; i < barcode.length(); i++) {
            int digit = Integer.parseInt(barcode.substring(i, i + 1));
            if (i % 2 == 0) {
                sum += digit * 1;
            } else {
                sum += digit * 3;
            }
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return String.valueOf(checkDigit);
    }
    public static long getProductId(String ean13Code) throws IllegalArgumentException {
        if (ean13Code == null || ean13Code.length() != 13) {
            throw new IllegalArgumentException("Invalid EAN13 code");
        }

        int[] ean13Array = new int[13];
        for (int i = 0; i < 13; i++) {
            ean13Array[i] = Character.getNumericValue(ean13Code.charAt(i));
        }

        if (ean13Array[0] != 8 || ean13Array[1] != 9 || ean13Array[2] != 0) {
            throw new IllegalArgumentException("Invalid EAN13 code");
        }

        long productId = 0;
        for (int i = 3; i < 10; i++) {
            productId = productId * 10 + ean13Array[i];
        }

        return productId;
    }

    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }

public void addListOfProducts(List<ProductDTO> products) {
    // Get the current year using LocalDate class
    LocalDate currentDate = LocalDate.now();
    int year = currentDate.getYear();
    String userName = "";
    for (ProductDTO productDTO : products) {
        String productName = productDTO.getProductName();
        double sellingPrice = productDTO.getSellingPrice();
        double existingStock = productDTO.getExistingStock();
        double buyingPrice = productDTO.getBuyingPrice();
        userName = productDTO.getUserName();

//        create and save product
        Product product = new Product();
        product.setName(productName);
        product.setSelling_price(sellingPrice);
        product.setUserName(userName);
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("No such user"));
        product.setUser(user);
        productRepository.save(product);

        String barcode = generateEAN13(String.valueOf(product.getProduct_id()));
        // Save the barcode with the product
        product.setBarCode(barcode);
        productRepository.save(product);

//        create and save inventory
        Inventory inventory = new Inventory();
        inventory.setProduct_id(product);
        inventory.setBuying_price(buyingPrice);
        inventory.setQuantity(existingStock);
        inventory.setUser(user);
        inventoryRepository.save(inventory);
    }
    User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
    StockBalance stockBalance = stockBalanceRepository.getOpeningStockByYearAndUser(year, user);
    if(stockBalance == null){
        tradingAccountService.addFirstOpeningStock(year,userName);
    }
}
    @Transactional
    public void editProduct(Integer id, String userName, String name, String gst, Double price) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        Product product = productRepository.findByIdAndUser(id,user);
        if(!name.equals("")){
            product.setName(name);
        }
        if(!gst.equals("")) {
            product.setGstCategory(GstCategory.valueOf(gst));
        }
        if(price!=null){
            product.setSelling_price(price);
        }
    }
}
