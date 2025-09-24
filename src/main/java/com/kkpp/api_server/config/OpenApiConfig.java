package com.kkpp.api_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        SecurityScheme xsrfHeaderScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("X-XSRF-TOKEN");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("xsrfHeader", xsrfHeaderScheme))
                .addSecurityItem(new SecurityRequirement().addList("xsrfHeader"))
                .info(new Info()
                        .title("냉장고 요리 API-Server Swagger")
                        .version("v1.0.0"));
    }
}
