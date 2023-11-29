package com.test.ana.rent;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RentService {
    @Autowired
    private final RentRepository rentRepository;
    @Autowired
    private final RentPaymentRepository rentPaymentRepository;
    @Autowired
    private final UserRepository userRepository;

    public void saveRentRecord(Rent record) {
        User user = userRepository.findByEmail(record.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        record.setUser(user);
        rentRepository.save(record);
    }

    public List<Rent> getRents(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        return rentRepository.findRentsByUser(user);
    }

    public void saveRentPayment(RentPayment record) {
        User user = userRepository.findByEmail(record.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        Date currentDate = new Date();
        rentPaymentRepository.save(record);
        record.setDate(currentDate);
        record.setUser(user);
        rentPaymentRepository.save(record);
    }

    public List<RentPayment> getRentPayment() {
        return rentPaymentRepository.findAll();
    }

    public Rent getRentById(Long id) {
        Rent item = rentRepository.findById(id).orElseThrow(()->new IllegalStateException("no such rent item"));
        return item;
    }
}
