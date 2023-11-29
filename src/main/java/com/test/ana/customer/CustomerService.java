package com.test.ana.customer;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final UserRepository userRepository;

    public void addCustomer(Customer customer) {
        User user = userRepository.findByEmail(customer.getUserName()).orElseThrow(() -> new IllegalStateException("no such user"));
        customer.setUser(user);
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return customerRepository.findAllCustomerByUser(user);
    }

    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }
}
