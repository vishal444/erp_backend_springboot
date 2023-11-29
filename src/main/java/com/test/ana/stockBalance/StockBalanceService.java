package com.test.ana.stockBalance;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockBalanceService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final StockBalanceRepository stockBalanceRepository;
    public StockBalance getOpeningStock(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        return stockBalanceRepository.getOpeningStockByUser(user);
    }
}
