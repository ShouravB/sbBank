package com.sbbank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Account")
public class AccountsDto {


    @NotEmpty(message = "Account numberCannot be empty")
    @Schema(description = "auto generated account number",example = "100876567890")
    private Long accountNumber;

    @NotEmpty(message = "Account type Cannot be empty")
    @Schema(description = "Savings or Checking",example = "Saving")
    private String accountType;

    @NotEmpty(message = "branch address cannot be empty")
    @Schema(description = "Address of Bank's particular branch ",example = "123 some street,city,state")
    private String branchAddress;
}
