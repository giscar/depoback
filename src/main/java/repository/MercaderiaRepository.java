package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Mercaderia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MercaderiaRepository extends ReactiveMongoRepository<Mercaderia, String> {
	
	Flux<Mercaderia> findByRuc(String ruc);
	
	@Aggregation(pipeline = {
			"{$group: { _id: null, maxField : { $max: '$codServicio' }}}"
	})
	Mono<Object> findMaxCodServicio();
	
}
