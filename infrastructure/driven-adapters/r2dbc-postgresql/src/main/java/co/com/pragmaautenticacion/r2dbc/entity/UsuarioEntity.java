package co.com.pragmaautenticacion.r2dbc.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
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
    @Column("id_usuario")
    private Long idUsuario;
    private String nombre;
    private String email;
    @Column("documento_identidad")
    private String documentoIdentidad;
    private String telefono;
    @Column("id_rol")
    private Long idRol;
    @Column("salario_base")
    private BigDecimal salarioBase;
}
