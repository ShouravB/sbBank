package com.sbbank.accounts.dto;

import lombok.Data;

/**
 *
 */
@Data
public final class AccountsMsgDto {
    private final Long accountNumber;
    private final String name;
    private final String email;
    private final String mobileNumber;
}
