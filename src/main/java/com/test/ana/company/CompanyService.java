package com.test.ana.company;

import com.test.ana.assets.Assets;
import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final UserRepository userRepository;
    public void saveCompanyData(Company company) {
        User user = userRepository.findByEmail(company.getUserName()).orElseThrow(()->new IllegalStateException("no such user"));
        company.setUser(user);
        companyRepository.save(company);
    }

    public Company getCompanyData(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return companyRepository.findByUser(user);
    }
    @Transactional
    public void updateCompany(String columnName,String userName, String  name, String address, double zip, double phone, double newValue) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        Company company = companyRepository.findByUser(user);
        switch (columnName) {
            case "companyName" -> company.setCompanyName(name);
            case "companyAddress" -> company.setCompanyAddress(address);
            case "zipCode" -> company.setZipCode(zip);
            case "companyPhoneNumber" -> company.setCompanyPhoneNumber(phone);
            case "gstNumber" -> company.setGstNumber(newValue);
        }
    }
}
