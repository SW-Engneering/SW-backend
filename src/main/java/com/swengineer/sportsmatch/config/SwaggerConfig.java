package com.swengineer.sportsmatch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Team Matching Board API")
                        .description("팀 매칭 게시판 및 매칭 시스템 API")
                        .version("1.0.0")
                );
    }
}
