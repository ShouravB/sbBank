package com.sbbank.accounts.service.impl;

import com.sbbank.accounts.constants.AccountConstants;
import com.sbbank.accounts.dto.CustomerDto;
import com.sbbank.accounts.entity.Accounts;
import com.sbbank.accounts.entity.Customer;
import com.sbbank.accounts.exceptions.CustomerAlreadyExistsException;
import com.sbbank.accounts.mapper.CustomerMapper;
import com.sbbank.accounts.repository.AccountsRepository;
import com.sbbank.accounts.repository.CustomerRepository;
import com.sbbank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(new Customer(),customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already present with number " + customerDto.getMobileNumber());
        }
        customer.setCreatedBy("Shourav Bhattarai");
        customer.setCreatedAt(LocalDateTime.now());
        //customer.setUpdatedAt(LocalDateTime.now());
       // customer.setUpdatedBy("Shourav Bhattarai");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setAccountNumber(1000335500L + new Random().nextInt(9000000));
        newAccount.setCreatedBy("Shourav Bhattarai");
        newAccount.setCreatedAt(LocalDateTime.now());
       // newAccount.setUpdatedAt(LocalDateTime.now());
       // newAccount.setUpdatedBy("Shourav Bhattarai");

        return newAccount;
    }
}
