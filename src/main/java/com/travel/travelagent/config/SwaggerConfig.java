package com.travel.travelagent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI travelAgentOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Travel Agent API")
                        .version("1.0")
                        .description("Travel Agent Management System APIs"));
    }
}