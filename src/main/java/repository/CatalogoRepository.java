package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Catalogo;
import reactor.core.publisher.Flux;

public interface CatalogoRepository extends ReactiveMongoRepository<Catalogo, String> {
	
	@Query("{'tipo' :  ?0 }")
	Flux<Catalogo> findByTipo(String tipo);
	
}
