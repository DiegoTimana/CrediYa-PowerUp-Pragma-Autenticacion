package co.com.pragmaautenticacion.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record UsuarioDTO(
        @Schema(description = "Identificador del usuario", example = "666")
        Long idUsuario,
        @Schema(description = "Nombres del usuario", example = "Diego")
        String nombre,
        @Schema(description = "Correo electrónico válido", example = "diego@mail.com")
        String email,
        @Schema(description = "Documento de identidad", example = "100023133")
        String documentoIdentidad,
        @Schema(description = "Teléfono", example = "21132132132")
        String telefono,
        @Schema(description = "Rol del usuario", example = "1")
        Long idRol,
        @Schema(description = "Salario base en COP", example = "2500000")
        BigDecimal salarioBase

) { }
