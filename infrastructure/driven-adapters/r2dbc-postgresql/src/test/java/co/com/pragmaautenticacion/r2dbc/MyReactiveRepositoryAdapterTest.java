package co.com.pragmaautenticacion.r2dbc;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.r2dbc.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyReactiveRepositoryAdapterTest {
    // TODO: change four you own tests

    @InjectMocks
    MyReactiveRepositoryAdapter repositoryAdapter;

    @Mock
    MyReactiveRepository repository;

    @Mock
    ObjectMapper mapper;

    private final UsuarioEntity usuarioEntity = UsuarioEntity.builder()
            .nombre("Diego")
            .email("alexistimana021@gmail.com")
            .documentoIdentidad("1004094901")
            .telefono("3122313232")
            .salarioBase(new BigDecimal(30000000))
            .build();

    private final Usuario usuario = Usuario.builder()
            .nombre("Diego")
            .email("alexistimana021@gmail.com")
            .documentoIdentidad("1004094901")
            .telefono("3122313232")
            .salarioBase(new BigDecimal(30000000))
            .build();

    @Test
    void mustSaveValue() {
        when(mapper.map(usuarioEntity, Usuario.class)).thenReturn(usuario);
        when(mapper.map(usuario, UsuarioEntity.class)).thenReturn(usuarioEntity);
        when(repository.save(usuarioEntity)).thenReturn(Mono.just(usuarioEntity));

        Mono<Usuario> result = repositoryAdapter.save(usuario);

        StepVerifier.create(result)
                .expectNext(usuario)
                .verifyComplete();
    }
}
