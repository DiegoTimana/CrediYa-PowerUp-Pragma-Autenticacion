package co.com.pragmaautenticacion.api.exceptionHandler;

import co.com.pragmaautenticacion.api.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Ejemplo: validaciones de negocio
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<org.springframework.http.ResponseEntity<ErrorResponseDTO>> handleIllegalArgument(
            IllegalArgumentException ex, ServerWebExchange exchange) {
        logger.warn("Error de validación: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), exchange);
    }

    // Ejemplo: duplicados (correo ya registrado)
    @ExceptionHandler(IllegalStateException.class)
    public Mono<org.springframework.http.ResponseEntity<ErrorResponseDTO>> handleIllegalState(
            IllegalStateException ex, ServerWebExchange exchange) {
        logger.warn("Error de estado: {}", ex.getMessage());
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), exchange);
    }

    // Cualquier otra excepción no controlada
    @ExceptionHandler(Exception.class)
    public Mono<org.springframework.http.ResponseEntity<ErrorResponseDTO>> handleGeneral(
            Exception ex, ServerWebExchange exchange) {
        logger.error("Error inesperado en la API", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ha ocurrido un error interno, por favor intente más tarde", exchange);
    }

    private Mono<org.springframework.http.ResponseEntity<ErrorResponseDTO>> buildResponse(
            HttpStatus status, String mensaje, ServerWebExchange exchange) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                mensaje,
                exchange.getRequest().getPath().toString(),
                LocalDateTime.now().toString()
        );
        return Mono.just(org.springframework.http.ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error));
    }
}
