package co.com.pragmaautenticacion.api;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.usecase.usuario.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);
    private final UsuarioUseCase usuarioUseCase;

    public Mono<ServerResponse> registrarUsuario(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Usuario.class)
                .doOnNext(usuario -> logger.info("Iniciando registro de usuario con correo: {}", usuario.getEmail()))
                .flatMap(usuarioUseCase::guardarUsuario)
                .doOnSuccess(usuarioGuardado -> logger.info("Usuario registrado exitosamente con id: {}", usuarioGuardado.getIdUsuario()))
                .flatMap(usuarioGuardado ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(usuarioGuardado)
                );
    }
}
