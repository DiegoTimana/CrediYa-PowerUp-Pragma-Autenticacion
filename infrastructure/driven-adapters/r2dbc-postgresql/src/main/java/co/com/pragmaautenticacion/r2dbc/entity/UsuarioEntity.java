package co.com.pragmaautenticacion.r2dbc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioEntity {
    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    private Long idRol;
    private BigDecimal salarioBase;
}
