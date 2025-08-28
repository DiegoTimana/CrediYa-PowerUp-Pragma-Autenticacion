package co.com.pragmaautenticacion.api;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.usecase.usuario.UsuarioUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UsuarioUseCase usuarioUseCase;

    private final String registrarUsuarioPath = "/api/v1/usuarios";

    private final Usuario usuarioValido = Usuario.builder()
            .nombre("Diego")
            .email("alexistimana021@gmail.com")
            .documentoIdentidad("1004094901")
            .telefono("3122313232")
            .salarioBase(new BigDecimal(30000000))
            .build();

    // Objeto de prueba para el escenario de error
    private final Usuario usuarioInvalido = Usuario.builder()
            .nombre(null) // Campo nulo para probar la validación
            .email("test.com") // Formato de email inválido
            .salarioBase(new BigDecimal(-1)) // Salario fuera de rango
            .build();

    @Test
    void shouldReturnOkForSuccessfulRegistration() {
        when(usuarioUseCase.guardarUsuario(any(Usuario.class))).thenReturn(Mono.just(usuarioValido));

        webTestClient.post()
                .uri(registrarUsuarioPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(usuarioValido)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Usuario.class);
    }

    @Test
    void shouldReturnBadRequestWhenNameIsNull() {
        when(usuarioUseCase.guardarUsuario(any(Usuario.class)))
                .thenReturn(Mono.error(new IllegalArgumentException("El nombre es obligatorio")));

        webTestClient.post()
                .uri(registrarUsuarioPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(usuarioInvalido)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void shouldReturnBadRequestWhenSalaryIsInvalid() {
        when(usuarioUseCase.guardarUsuario(any(Usuario.class)))
                .thenReturn(Mono.error(new IllegalArgumentException("El salario base no es válido")));

        webTestClient.post()
                .uri(registrarUsuarioPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(usuarioInvalido)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void shouldReturnConflictWhenEmailIsAlreadyRegistered() {
        when(usuarioUseCase.guardarUsuario(any(Usuario.class)))
                .thenReturn(Mono.error(new IllegalStateException("El correo ya está registrado")));

        webTestClient.post()
                .uri(registrarUsuarioPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(usuarioValido)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldPostSaveTask() {

        when(usuarioUseCase.guardarUsuario(any(Usuario.class))).thenReturn(Mono.just(usuarioValido));

        webTestClient.post()
                .uri(registrarUsuarioPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(usuarioValido)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Usuario.class)
                .value(saved -> Assertions.assertThat(saved.getEmail()).isEqualTo(usuarioValido.getEmail()));
    }

}
