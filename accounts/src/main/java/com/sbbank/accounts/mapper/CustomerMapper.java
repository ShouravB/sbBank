package com.sbbank.accounts.mapper;

import com.sbbank.accounts.dto.CustomerDto;
import com.sbbank.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){

        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return  customerDto;
    }

    public static Customer mapToCustomer(Customer customer, CustomerDto customerDto){

        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
