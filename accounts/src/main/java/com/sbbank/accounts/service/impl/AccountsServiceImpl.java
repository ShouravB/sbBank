package com.sbbank.accounts.service.impl;

import com.sbbank.accounts.dto.CustomerDto;
import com.sbbank.accounts.repository.AccountsRepository;
import com.sbbank.accounts.repository.CustomerRepository;
import com.sbbank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
