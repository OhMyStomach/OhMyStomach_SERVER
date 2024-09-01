package org.ohmystomach.ohmystomach_server.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("흡변구역 API")
                        .version("v1.0.0").
                        description("아이고배야 팀에서 만든 흡변구역 서비스 API입니다.")
                );
    }
}
