package com.sbbank.accounts.service;

import com.sbbank.accounts.dto.CustomerDetailsDto;
import com.sbbank.accounts.dto.CustomerDto;

public interface ICustomerService {
    CustomerDetailsDto getCustomerDetails(String mobileNumber);
}
