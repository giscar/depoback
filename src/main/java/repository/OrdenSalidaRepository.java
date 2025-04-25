package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.OrdenSalida;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdenSalidaRepository extends ReactiveMongoRepository<OrdenSalida, String> {
	
	@Aggregation(pipeline = {
			"{$group: { _id: null, maxField : { $max: '$numeroRecepcion' }}}"
	})
	Mono<Object> findMaxOrdenSalida();
	
	@Query("{'idIngreso' :  ?0 }")
	Flux<OrdenSalida> findByCodIngreso(String idIngreso);
}
