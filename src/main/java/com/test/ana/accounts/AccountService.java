package com.test.ana.accounts;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void addAccountData(List<AccountData> data) {
    accountRepository.saveAll(data);
}
    public AccountData getBalanceSheet() {
        return null;
    }
}
