package com.sbbank.accounts.service.client;

import com.sbbank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;

@FeignClient(name="loans",fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping("/api/v1/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
