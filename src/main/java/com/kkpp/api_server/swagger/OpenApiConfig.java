package com.kkpp.api_server.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Skeleton API")
                        .description("Simple skeleton for controller/service/swagger")
                        .version("v1.0.0")
                        .contact(new Contact().name("Dev").email("dev@example.com")));
    }
}