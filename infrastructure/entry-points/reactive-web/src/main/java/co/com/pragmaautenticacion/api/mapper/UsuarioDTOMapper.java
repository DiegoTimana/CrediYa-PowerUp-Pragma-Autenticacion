package co.com.pragmaautenticacion.api.mapper;

import co.com.pragmaautenticacion.api.dto.CrearUsuarioDTO;
import co.com.pragmaautenticacion.model.usuario.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    CrearUsuarioDTO toResponse(Usuario usuario);

    Usuario toModel(CrearUsuarioDTO usuarioDTO);

    List<CrearUsuarioDTO> toResponseList(List<CrearUsuarioDTO> usuarios);
}
