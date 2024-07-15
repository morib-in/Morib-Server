package org.sopt.jaksim.global.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Morib 스웩")
                .description("Morib Swagger")
                .version("v1");

        Server server = new Server();
        server.url("https://api.morib.in");

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .servers(List.of(server));
    }
}
