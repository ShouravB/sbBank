package com.sbbank.accounts.functions;

import com.sbbank.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

//@AllArgsConstructor
@Configuration
public class AccountsMessageFunctions {

    private static final Logger logger = LoggerFactory.getLogger(AccountsMessageFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService){
        return accountNumber->{

            logger.info("Updating Communication status for Accounts" + accountNumber.toString());
            accountsService.updateCommunicationStatus(accountNumber);
        };
    }



//    private static StreamBridge streamBridge;
//
//    public static void sendEmailSms(Accounts accounts, Customer customer){
//        var accountsMsgDto = new AccountsMsgDto(accounts.getAccountNumber(), customer.getName(),
//                customer.getEmail(), customer.getMobileNumber());
//        logger.info("Sending Communication request for the details: {}", accountsMsgDto);
//        var result = streamBridge.send("sendEmailSms-out-0", accountsMsgDto);
//        logger.info("Is the Communication request successfully triggered ? : {}", result);
//    }
}
