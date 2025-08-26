package co.com.pragmaautenticacion.usecase.usuario;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.model.usuario.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class UsuarioUseCase {
    //reglas de negocio, llama a user repository

    private final UsuarioRepository usuarioRepository;

    //validar aqui
    public void guardarUsuario(Usuario usuario){
        usuarioRepository.guardarUsuario(usuario);
    }
}
