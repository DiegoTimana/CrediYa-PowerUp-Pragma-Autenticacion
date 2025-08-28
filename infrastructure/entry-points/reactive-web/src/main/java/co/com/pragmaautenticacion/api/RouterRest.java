package co.com.pragmaautenticacion.api;

import co.com.pragmaautenticacion.api.dto.CrearUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Gestión de usuarios del sistema")
public class RouterRest {

    private final Handler usuarioHandler;

    @Bean
    @RouterOperation(
            path = "/api/v1/usuarios",
            produces = {"application/json"},
            method = RequestMethod.POST,
            beanClass = Handler.class,
            beanMethod = "registrarUsuario",
            operation = @Operation(
                    summary = "Registrar usuario",
                    description = "Registra un nuevo usuario en el sistema con sus datos personales",
                    tags = {"Usuarios"},
                    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            required = true,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CrearUsuarioDTO.class)
                            )
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Usuario creado",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CrearUsuarioDTO.class)
                                    )
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Bad Request"
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/v1/usuarios"), usuarioHandler::registrarUsuario);
    }
}
