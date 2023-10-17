package com.sbbank.accounts.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class AccountsDto {


    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
