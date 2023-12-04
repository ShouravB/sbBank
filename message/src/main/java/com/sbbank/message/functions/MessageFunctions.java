package com.sbbank.message.functions;

import com.sbbank.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    public static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email(){

        return accountsMessageDto ->{
            log.info("email is sent with details : " + accountsMessageDto.toString());
            return accountsMessageDto;
        };
    }

    @Bean
    public Function<AccountsMsgDto,Long> sms(){
        return accountsMsgDto -> {
            log.info("Sms sent to mobile number :" + accountsMsgDto.mobileNumber());
            return accountsMsgDto.accountNumber();
        };
    }
}
