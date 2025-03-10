package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Catalogo;
import model.NotaRecepcion;
import reactor.core.publisher.Flux;

public interface NotaRecepcionRepository extends ReactiveMongoRepository<NotaRecepcion, String> {
	
	@Query("{'tipo' :  ?0 }")
	Flux<Catalogo> findByTipo(String tipo);
	
}
