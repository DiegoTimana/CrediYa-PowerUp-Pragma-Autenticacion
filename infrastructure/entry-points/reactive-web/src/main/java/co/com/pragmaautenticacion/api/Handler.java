package co.com.pragmaautenticacion.api;

import co.com.pragmaautenticacion.api.dto.CrearUsuarioDTO;
import co.com.pragmaautenticacion.api.dto.UsuarioDTO;
import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.model.usuario.gateways.UsuarioRepository;
import co.com.pragmaautenticacion.usecase.usuario.UsuarioUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    public Mono<ServerResponse> listenRegistrarUsuario(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CrearUsuarioDTO.class)
                .map(usuarioDTO -> objectMapper.convertValue(usuarioDTO, Usuario.class))
                .doOnNext(usuario -> logger.info("Iniciando registro de usuario con correo: {}", usuario.getEmail()))
                .flatMap(usuarioUseCase::guardarUsuario)
                .doOnSuccess(usuarioGuardado -> logger.info("Usuario registrado exitosamente con id: {}", usuarioGuardado.getIdUsuario()))
                .flatMap(usuarioGuardado ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(objectMapper.convertValue(usuarioGuardado, UsuarioDTO.class))
                );
    }
}
