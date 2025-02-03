package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Salida;
import reactor.core.publisher.Flux;

public interface SalidaRepository extends ReactiveMongoRepository<Salida, String> {
	
	@Query("{'numeroMercaderia': '?1'})")
	Flux<Salida> findByNumeroMercaderia(String numeroMercaderia);
	
}
