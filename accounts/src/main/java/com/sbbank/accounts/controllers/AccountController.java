package com.sbbank.accounts.controllers;

import com.sbbank.accounts.constants.AccountConstants;
import com.sbbank.accounts.dto.AccountsDto;
import com.sbbank.accounts.dto.CustomerDto;
import com.sbbank.accounts.dto.ResponseDto;
import com.sbbank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    private IAccountsService iAccountsService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){

        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }

    @GetMapping(value="/fetch")
    public ResponseEntity<CustomerDto> getCustomerByMobileNumber(@RequestParam String mobileNUmber){

       CustomerDto customerDto = iAccountsService.getAccountByMobileNumber(mobileNUmber);

       return ResponseEntity.status(HttpStatus.OK)
               .body(customerDto);

    }

    @PutMapping(value ="/update")
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody CustomerDto customerDto){
       boolean isUpdated = iAccountsService.updateCustomer(customerDto);
       if(isUpdated){
           return ResponseEntity.status(HttpStatus.OK)
                   .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
       }else{
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
       }

    }


}
