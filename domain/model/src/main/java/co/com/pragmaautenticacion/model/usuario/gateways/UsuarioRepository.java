package co.com.pragmaautenticacion.model.usuario.gateways;

import co.com.pragmaautenticacion.model.usuario.Usuario;

import java.util.List;

public interface UsuarioRepository {

    void guardarUsuario(Usuario usuario);

    List<Usuario> listarUsuarios();

    Usuario getUsuarioPorId(long idUsuario);

    Usuario editarUsuario(Usuario usuario);

    void eliminarUsuario(long idUsuario);
}
