package co.com.pragmaautenticacion.usecase.usuario;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.model.usuario.gateways.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");
        usuario.setSalarioBase(new BigDecimal("10000000"));
    }

    @Test
    void guardarUsuario_DeberiaGuardarExitosamente_CuandoDatosSonValidos() {
        // Arrange
        when(usuarioRepository.existePorEmail(anyString())).thenReturn(Mono.just(false));
        when(usuarioRepository.guardar(any(Usuario.class))).thenReturn(Mono.just(usuario));

        // Act
        Mono<Usuario> resultado = usuarioUseCase.guardarUsuario(usuario);

        // Assert
        StepVerifier.create(resultado)
                .expectNext(usuario)
                .verifyComplete();

        verify(usuarioRepository, times(1)).existePorEmail(usuario.getEmail());
        verify(usuarioRepository, times(1)).guardar(usuario);
    }

    @Test
    void guardarUsuario_DeberiaLanzarError_CuandoNombreEsNulo() {
        // Arrange
        usuario.setNombre(null);

        // Act
        Mono<Usuario> resultado = usuarioUseCase.guardarUsuario(usuario);

        // Assert
        StepVerifier.create(resultado)
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().equals("El nombre es obligatorio"))
                .verify();

        verify(usuarioRepository, never()).existePorEmail(anyString());
        verify(usuarioRepository, never()).guardar(any(Usuario.class));
    }

    @Test
    void guardarUsuario_DeberiaLanzarError_CuandoEmailYaExiste() {
        // Arrange
        when(usuarioRepository.existePorEmail(anyString())).thenReturn(Mono.just(true));

        // Act
        Mono<Usuario> resultado = usuarioUseCase.guardarUsuario(usuario);

        // Assert
        StepVerifier.create(resultado)
                .expectErrorMatches(e -> e instanceof IllegalStateException &&
                        e.getMessage().equals("El correo ya está registrado"))
                .verify();

        verify(usuarioRepository, times(1)).existePorEmail(usuario.getEmail());
        verify(usuarioRepository, never()).guardar(any(Usuario.class));
    }

    @Test
    void guardarUsuario_DeberiaLanzarError_CuandoSalarioEsNegativo() {
        // Arrange
        usuario.setSalarioBase(new BigDecimal("-100"));

        // Act
        Mono<Usuario> resultado = usuarioUseCase.guardarUsuario(usuario);

        // Assert
        StepVerifier.create(resultado)
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().equals("El salario base no es válido"))
                .verify();

        verify(usuarioRepository, never()).existePorEmail(anyString());
        verify(usuarioRepository, never()).guardar(any(Usuario.class));
    }

    @Test
    void guardarUsuario_DeberiaLanzarError_CuandoSalarioEsSuperiorAlLimite() {
        // Arrange
        usuario.setSalarioBase(new BigDecimal("15000001"));

        // Act
        Mono<Usuario> resultado = usuarioUseCase.guardarUsuario(usuario);

        // Assert
        StepVerifier.create(resultado)
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().equals("El salario base no es válido"))
                .verify();

        verify(usuarioRepository, never()).existePorEmail(anyString());
        verify(usuarioRepository, never()).guardar(any(Usuario.class));
    }
}