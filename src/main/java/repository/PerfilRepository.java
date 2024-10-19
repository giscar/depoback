package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Perfil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerfilRepository extends ReactiveMongoRepository<Perfil, String> {
	

	@Query("{'estado': '1'}")
	Flux<Perfil> findByEstado();
	
	@Query("{'estado': '1', 'codigo' :  ?0 }")	
	Mono<Perfil> findByEstadoByCodigo(String codigo);
}
