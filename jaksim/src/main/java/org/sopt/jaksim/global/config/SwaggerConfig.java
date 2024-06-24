package org.sopt.jaksim.global.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Jaksim Swagger")
                .description("Jaksim Swagger dev API")
                .version("v1");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
