package com.sbbank.accounts.mapper;

import com.sbbank.accounts.dto.AccountsDto;
import com.sbbank.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountDto(Accounts account, AccountsDto accountsDto){

        accountsDto.setAccountNumber(account.getAccountNumber());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchAddress(account.getBranchAddress());

        return accountsDto;
    }

    public static Accounts mapToAccount(Accounts accounts, AccountsDto accountsDto){
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
