package com.sbbank.accounts.service.impl;

import com.sbbank.accounts.constants.AccountConstants;
import com.sbbank.accounts.dto.AccountsDto;
import com.sbbank.accounts.dto.CustomerDto;
import com.sbbank.accounts.entity.Accounts;
import com.sbbank.accounts.entity.Customer;
import com.sbbank.accounts.exceptions.CustomerAlreadyExistsException;
import com.sbbank.accounts.exceptions.ResourceNotFoundException;
import com.sbbank.accounts.mapper.AccountsMapper;
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

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }


    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setAccountNumber(1000335500L + new Random().nextInt(9000000));

        return newAccount;
    }

    @Override
    public CustomerDto getAccountByMobileNumber(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
       Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
               ()->new ResourceNotFoundException("Account", "Mobile number", customer.getMobileNumber())
       );

       CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
       customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(account,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        boolean isUpdate = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null){
            Accounts account = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccount(account,accountsDto);
            account = accountsRepository.save(account);


            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer", "customer id", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customer,customerDto);
            customerRepository.save(customer);
            isUpdate =true;
        }

       return isUpdate;



    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );

//        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
//                ()->new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
//        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());


        return true;
    }

}
