package com.test.ana.salary;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class SalaryService {
    @Autowired
    private final SalaryRepository salaryRepository;
    @Autowired
    private final UserRepository userRepository;

    public void saveSalaryRecord(Salary record) {
        User user = userRepository.findByEmail(record.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        Date currentDate = new Date();
        salaryRepository.save(record);
        record.setDate(currentDate);
        record.setUser(user);
        salaryRepository.save(record);
    }
}
