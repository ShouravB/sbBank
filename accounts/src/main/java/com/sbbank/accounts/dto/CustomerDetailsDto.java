package com.sbbank.accounts.dto;

import lombok.Data;

@Data
public class CustomerDetailsDto {

    private CustomerDto customerDto;

    private LoansDto loansDto;

    private CardsDto cardsDto;
}
