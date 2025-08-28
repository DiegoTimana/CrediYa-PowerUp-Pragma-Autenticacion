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
    @Column(name = "documento_identidad")
    private String documentoIdentidad;
    private String telefono;
    @Column(name = "id_rol")
    private Long idRol;
    @Column(name = "salario_base")
    private BigDecimal salarioBase;
}
