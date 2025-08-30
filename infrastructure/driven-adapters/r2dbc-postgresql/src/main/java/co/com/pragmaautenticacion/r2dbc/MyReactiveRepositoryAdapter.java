package co.com.pragmaautenticacion.r2dbc;

import co.com.pragmaautenticacion.model.usuario.Usuario;
import co.com.pragmaautenticacion.model.usuario.gateways.UsuarioRepository;
import co.com.pragmaautenticacion.r2dbc.entity.UsuarioEntity;
import co.com.pragmaautenticacion.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Usuario,
        UsuarioEntity,
        Long,
        MyReactiveRepository>
    implements UsuarioRepository {

    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, entity -> mapper.map(entity, Usuario.class));
    }

    @Override
    @Transactional
    public Mono<Usuario> guardar(Usuario usuario) {
        return super.save(usuario);
    }

    @Override
    public Mono<Boolean> existePorEmail(String email) {
        return repository.existsByEmail(email);
    }
}
