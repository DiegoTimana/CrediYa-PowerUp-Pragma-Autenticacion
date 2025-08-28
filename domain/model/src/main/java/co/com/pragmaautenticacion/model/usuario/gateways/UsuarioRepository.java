package co.com.pragmaautenticacion.model.usuario.gateways;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UsuarioRepository {

    Mono<Usuario> guardar(Usuario usuario);
    Mono<Boolean> existePorEmail(String email);


}
