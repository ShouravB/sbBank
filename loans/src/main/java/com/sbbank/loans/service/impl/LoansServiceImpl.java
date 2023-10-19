package com.sbbank.loans.service.impl;

import com.sbbank.loans.constants.LoansConstants;
import com.sbbank.loans.dto.LoansDto;
import com.sbbank.loans.entity.Loans;
import com.sbbank.loans.exception.LoanAlreadyExistsException;
import com.sbbank.loans.exception.ResourceNotFoundException;
import com.sbbank.loans.mapper.LoansMapper;
import com.sbbank.loans.repository.LoansRepository;
import com.sbbank.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     *
     */
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists with given Mobile Number : "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));

    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans fetchedLoan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Loans" , "mobile number", mobileNumber)
                );
        return LoansMapper.mapToLoansDto(fetchedLoan,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {

        log.info("**************FROM LoansServiceImpl*****************");
        log.info(loansDto.toString());

        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loans", "Loan number", loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);

        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","mobile number",mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 10000000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
