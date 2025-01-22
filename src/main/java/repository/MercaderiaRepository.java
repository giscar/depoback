package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Mercaderia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MercaderiaRepository extends ReactiveMongoRepository<Mercaderia, String> {
	
	@Aggregation(pipeline = {
			"{$group: { _id: null, maxField : { $max: '$codServicio' }}}"
	})
	Mono<Object> findMaxCodServicio();
	
	@Aggregation(pipeline = {
			"{ $lookup:{ from : 'catalogos', localField: 'unidadMedida', foreignField: 'codigo', as: 'um'}}, ",
		    "{ $match: { 'um.tipo' : '1',  'idIngreso' : ?0}} "
			
	})
	Flux<Mercaderia> findByIngreso(String idIngreso);
	
	
	
	
}
