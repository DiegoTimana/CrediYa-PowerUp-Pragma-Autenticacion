package co.com.pragmaautenticacion.model.usuario;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Usuario {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    private Long idRol;
    private BigDecimal salarioBase;
}
