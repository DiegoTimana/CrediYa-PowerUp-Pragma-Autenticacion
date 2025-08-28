package co.com.pragmaautenticacion.r2dbc;

import co.com.pragmaautenticacion.r2dbc.entity.UsuarioEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

// TODO: This file is just an example, you should delete or modify it
public interface MyReactiveRepository extends ReactiveCrudRepository<UsuarioEntity, Long>, ReactiveQueryByExampleExecutor<UsuarioEntity> {

    Mono<Boolean> existsByEmail(String email);

}
