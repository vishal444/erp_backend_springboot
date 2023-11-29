package com.test.ana.loans;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanService {
    @Autowired
    private final LoanRepository loanRepository;
    @Autowired
    private final LoanPaymentRepository loanPaymentRepository;
    @Autowired
    private final UserRepository userRepository;
    public void saveLoanRecord(Loan record) {
        User user = userRepository.findByEmail(record.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        record.setUser(user);
        loanRepository.save(record);
    }

    public List<Loan> getLoans(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        return loanRepository.findLoansByUser(user);
    }

    public Loan getLoanById(Long id) {
        Loan item = loanRepository.findById(id).orElseThrow(()->new IllegalStateException("no such loan"));
        return item;
    }

    public void saveLoanPayment(LoanPayment record) {
        User user = userRepository.findByEmail(record.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        Date currentDate = new Date();
        loanPaymentRepository.save(record);
        record.setUser(user);
        record.setDate(currentDate);
        loanPaymentRepository.save(record);
    }
}
