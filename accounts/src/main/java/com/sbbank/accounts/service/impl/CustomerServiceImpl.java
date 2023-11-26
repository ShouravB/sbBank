package com.sbbank.accounts.service.impl;

import com.sbbank.accounts.dto.*;
import com.sbbank.accounts.entity.Accounts;
import com.sbbank.accounts.entity.Customer;
import com.sbbank.accounts.exceptions.ResourceNotFoundException;
import com.sbbank.accounts.mapper.AccountsMapper;
import com.sbbank.accounts.mapper.CustomerMapper;
import com.sbbank.accounts.repository.AccountsRepository;
import com.sbbank.accounts.repository.CustomerRepository;
import com.sbbank.accounts.service.ICustomerService;
import com.sbbank.accounts.service.client.CardsFeignClient;
import com.sbbank.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto getCustomerDetails(String mobileNumber) {

        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobile number",mobileNumber)
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "MobileNumber",mobileNumber)
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(account,new AccountsDto()));
        customerDetailsDto.setCustomerDto(customerDto);

        ResponseEntity<CardsDto> cardDetails = cardsFeignClient.fetchCardDetails(mobileNumber);
        if(cardDetails != null){
            customerDetailsDto.setCardsDto(cardDetails.getBody());
        }
        ResponseEntity<LoansDto> loansDetails = loansFeignClient.fetchLoanDetails(mobileNumber);
        if(loansDetails != null){
            customerDetailsDto.setLoansDto(loansDetails.getBody());
        }





        return customerDetailsDto;
    }
}
