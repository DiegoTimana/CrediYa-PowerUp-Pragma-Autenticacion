package co.com.pragmaautenticacion.usecase.usuario;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.model.usuario.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class UsuarioUseCase {
    //reglas de negocio, llama a user repository

    private final UsuarioRepository usuarioRepository;

    //validar aqui
    public Mono<Usuario> guardarUsuario(Usuario usuario) {
        // Validaciones de negocio
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            return Mono.error(new IllegalArgumentException("El nombre es obligatorio"));
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            return Mono.error(new IllegalArgumentException("El correo es obligatorio"));
        }
        // Crea los valores de comparación
        BigDecimal cero = new BigDecimal(0);
        BigDecimal limiteSuperior = new BigDecimal(15000000);

        if (usuario.getSalarioBase() == null ||
                usuario.getSalarioBase().compareTo(cero) <= 0 ||
                usuario.getSalarioBase().compareTo(limiteSuperior) > 0) {
            return Mono.error(new IllegalArgumentException("El salario base no es válido"));
        }

        return usuarioRepository.existePorEmail(usuario.getEmail())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new IllegalStateException("El correo ya está registrado"));
                    }
                    return usuarioRepository.guardar(usuario);
                });
    }
}
