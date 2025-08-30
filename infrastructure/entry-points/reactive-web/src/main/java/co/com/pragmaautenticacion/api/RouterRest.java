package co.com.pragmaautenticacion.api;

import co.com.pragmaautenticacion.api.config.OpenApiSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

    private final Handler usuarioHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        //return route(POST("/api/v1/usuarios"), usuarioHandler::registrarUsuario);
        return route()
                .POST("/api/v1/usuarios", usuarioHandler::listenRegistrarUsuario, OpenApiSwagger::registrarUsuario)
                .build();
    }
}
