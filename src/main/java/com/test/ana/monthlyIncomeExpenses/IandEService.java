package com.test.ana.monthlyIncomeExpenses;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class IandEService {
    @Autowired
    private final IandERepository iandERepository;
    @Autowired
    private final UserRepository userRepository;
    public void saveIncomeExpense(IncomeAndExpenses record) {
        User user = userRepository.findByEmail(record.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        Date currentDate = new Date();
        iandERepository.save(record);
        record.setDate(currentDate);
        record.setUser(user);
        iandERepository.save(record);
    }
}
