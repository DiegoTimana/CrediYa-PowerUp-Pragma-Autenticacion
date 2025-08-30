package co.com.pragmaautenticacion.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorResponseDTO(
        @Schema(description = "Estado HTTP de la respuesta de error", example = "409")
        int status,
        @Schema(description = "Mensaje de la respuesta de error", example = "El correo ya está registrado")
        String mensaje,
        @Schema(description = "Ruta del endpoint que retorno la respuesta de error", example = "/api/v1/usuarios")
        String path,
        @Schema(description = "Marca de tiempo de cuando se originó la respuesta de error", example = "2025-08-30T16:01:08.752384400")
        String timestamp
) {

}
