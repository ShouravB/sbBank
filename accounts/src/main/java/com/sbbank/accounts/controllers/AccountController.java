package com.sbbank.accounts.controllers;

import com.sbbank.accounts.constants.AccountConstants;
import com.sbbank.accounts.dto.AccountsDto;
import com.sbbank.accounts.dto.CustomerDto;
import com.sbbank.accounts.dto.ResponseDto;
import com.sbbank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Tag(name = "Accounts Customer APIs",description = "CRUD Rest APIs to manage accounts and customers ")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Customer & Account API",
            description = "Create a new Customer and Account inside sbBank"
    )
    @ApiResponse(
            responseCode = AccountConstants.STATUS_201,
            description = AccountConstants.MESSAGE_201
    )
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Customer API",
            description = "Get a specific Customer and Account details from provided mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @GetMapping(value = "/fetch")
    public ResponseEntity<CustomerDto> getCustomerByMobileNumber(@RequestParam
                                                                 @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number invalid") String mobileNUmber) {

        CustomerDto customerDto = iAccountsService.getAccountByMobileNumber(mobileNUmber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);

    }

    @Operation(
            summary = "Update Customer API",
            description = "Update the information of a specific Customer and their accounts"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_200,
                    description = AccountConstants.MESSAGE_200
            ),
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_500,
                    description = AccountConstants.MESSAGE_500
            )
    })
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateCustomer(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }

    }

    @Operation(
            summary = "Delete Customer API",
            description = "Delete the Customer and account information associated with given mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_200,
                    description = AccountConstants.MESSAGE_200
            ),
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_500,
                    description = AccountConstants.MESSAGE_500
            )
    })
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam
                                                          @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number invalid") String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }


}
