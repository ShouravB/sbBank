package com.sbbank.accounts.configuration;


import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI(){
//        Server devServer = new Server();
//        devServer.setUrl(devUrl);
//        devServer.setDescription("Server URL in Development environment");
//
//        Server prodServer = new Server();
//        prodServer.setUrl(prodUrl);
//        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("shourav.bhattarai@gmail.com");
        contact.setName("Shourav Bhattarai");
        contact.setUrl("https://github.com/ShouravB/sbBank");

        License myLicense = new License().name("No License").url("shouravbhattarai.info");

        Info info = new Info()
                .title("sbBank Account Service")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage account services of sbBank.").termsOfService("shouravbhattarai.info")
                .license(myLicense);

        return new OpenAPI().info(info);
    }


}
