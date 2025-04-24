package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Salida;
import reactor.core.publisher.Flux;

public interface SalidaRepository extends ReactiveMongoRepository<Salida, String> {
	
	@Query("{'numeroMercaderia': ?0 }")
	Flux<Salida> findByNumeroMercaderia(String numeroMercaderia);
	
	@Query("{'idIngreso': ?0, 'idOrdenSalida' : '' }")
	Flux<Salida> findByIngreso(String idIngreso);
	
}
