package co.com.pragmaautenticacion.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Autenticación - CrediYa")
                        .version("1.0.0")
                        .description("Microservicio para gestión de usuarios con WebFlux"));
    }
}
