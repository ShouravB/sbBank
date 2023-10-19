package com.sbbank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;


@Data
@Schema(name = "Customer",description = "Dto to hold Customer info")
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 30, message = "Name length should be 5 to 30")
    @Schema(
            description = "Name of the Customer",example = "Elon Musk"
    )
    private String name;

    @NotEmpty(message = "email address cannot be empty")
    @Email(message = "Invalid email address")
    @Schema(description = "Valid email id",example = "elon.musk@tesla.com")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number invalid")
    @Schema(description = "10 digits mobile number",example = "1234567890")
    private String mobileNumber;

    @Schema(description = "Dto to hold Account information for this customer")
    private AccountsDto accountsDto;
}
