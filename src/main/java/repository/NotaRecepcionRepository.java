package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.NotaRecepcion;
import reactor.core.publisher.Mono;

public interface NotaRecepcionRepository extends ReactiveMongoRepository<NotaRecepcion, String> {
	
	
	@Aggregation(pipeline = {
			"{$group: { _id: null, maxField : { $max: '$numeroRecepcion' }}}"
	})
	Mono<Object> findMaxNumeroRecepcion();
	
}
