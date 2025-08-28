package co.com.pragmaautenticacion.api.config;

import co.com.pragmaautenticacion.api.Handler;
import co.com.pragmaautenticacion.api.RouterRest;
import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.usecase.usuario.UsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
@Import({CorsConfig.class, SecurityHeadersConfig.class})
class ConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UsuarioUseCase usuarioUseCase;

    private final Usuario usuario = Usuario.builder()
            .nombre("Diego")
            .email("alexistimana021@gmail.com")
            .documentoIdentidad("1004094901")
            .telefono("3122313232")
            .salarioBase(new BigDecimal(30000000))
            .build();

    private final Usuario usuario2 = Usuario.builder()
            .nombre("Alexis")
            .email("Admtr@gmail.com")
            .documentoIdentidad("290219")
            .telefono("3434114")
            .salarioBase(new BigDecimal(40000000))
            .build();


    @Test
    void corsConfigurationShouldAllowOrigins() {
//        webTestClient.get()
//                .uri("/api/v1/usuarios")
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().valueEquals("Content-Security-Policy",
//                        "default-src 'self'; frame-ancestors 'self'; form-action 'self'")
//                .expectHeader().valueEquals("Strict-Transport-Security", "max-age=31536000;")
//                .expectHeader().valueEquals("X-Content-Type-Options", "nosniff")
//                .expectHeader().valueEquals("Server", "")
//                .expectHeader().valueEquals("Cache-Control", "no-store")
//                .expectHeader().valueEquals("Pragma", "no-cache")
//                .expectHeader().valueEquals("Referrer-Policy", "strict-origin-when-cross-origin");
    }

}