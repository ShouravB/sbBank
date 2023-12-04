package com.sbbank.accounts.service;

import com.sbbank.accounts.dto.CustomerDto;

public interface IAccountsService {


    /**
     *
     * @param customerDto
     */
   public void createAccount(CustomerDto customerDto);

   CustomerDto getAccountByMobileNumber(String mobileNumber);

    boolean updateCustomer(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);

}
