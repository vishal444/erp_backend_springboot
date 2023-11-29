package com.test.ana.overDraft;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ODService {
    @Autowired
    private final ODRepository odRepository;
    @Autowired
    private final ODPaymentRepository odPaymentRepository;
    @Autowired
    private final UserRepository userRepository;
//    @Transactional
//    public void addODAmount(Double odWithdrawn) {
//        if(odRepository.getTotalODWithdrawnAmount() == null){
//            OverDraft overDraft = new OverDraft();
//            overDraft.setWithdrawnAmount(odWithdrawn);
//            odRepository.save(overDraft);
//        } else {
//            double totalODAmount = odRepository.getTotalODWithdrawnAmount();
//            totalODAmount = totalODAmount + odWithdrawn;
//            OverDraft overDraft = new OverDraft();
//            overDraft.setWithdrawnAmount(totalODAmount);
//            odRepository.save(overDraft);
//        }
//    }

    public void saveODPayment(ODPayment odPayment) {
        User user = userRepository.findByEmail(odPayment.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        Date currentDate = new Date();
        odPaymentRepository.save(odPayment);
        odPayment.setDate(currentDate);
        odPayment.setUser(user);
        odPaymentRepository.save(odPayment);
    }

    public void saveODAmount(OverDraft overDraft) {
        User user = userRepository.findByEmail(overDraft.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        overDraft.setUser(user);
        Date currentDate = new Date();
        overDraft.setDate(currentDate);
        odRepository.save(overDraft);
    }
}
