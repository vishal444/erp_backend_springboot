package com.test.ana.employees;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final UserRepository userRepository;

    public void addStaff(Employee staff) {
        User user = userRepository.findByEmail(staff.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        staff.setUser(user);
        employeeRepository.save(staff);
    }

    public List<Employee> getStaff(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        return employeeRepository.findEmployeesByUserName(user);
    }

    public Employee getEmployeeById(Long id) {
        Employee item = employeeRepository.findById(id).orElseThrow(()->new IllegalStateException("no such employee"));
        return item;
    }
}
