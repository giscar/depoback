package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Mercaderia;
import reactor.core.publisher.Flux;

public interface MercaderiaRepository extends ReactiveMongoRepository<Mercaderia, String> {
	
	Flux<Mercaderia> findByRuc(String ruc);
	
}
