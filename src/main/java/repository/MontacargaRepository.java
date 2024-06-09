package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Montacarga;
import reactor.core.publisher.Flux;

public interface MontacargaRepository extends ReactiveMongoRepository<Montacarga, String> {
	
	@Query("{'estado': '1'}")
	Flux<Montacarga> findByEstado();

}
