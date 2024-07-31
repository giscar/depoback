package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Operador;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperadorRepository extends ReactiveMongoRepository<Operador, String> {
	

	@Query("{'estado': '1'}")
	Flux<Operador> findByEstado();
	
	@Query("{'estado': '1', 'documento' :  ?0 }")
	Mono<Operador> findByEstadoByDocumento(String documento);
}
