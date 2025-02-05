package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Mercaderia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MercaderiaRepository extends ReactiveMongoRepository<Mercaderia, String> {
	
	@Aggregation(pipeline = {
			"{$group: { _id: null, maxField : { $max: '$codMercaderia' }}}"
	})
	Mono<Object> findMaxCodMercaderia();
	
	@Aggregation(pipeline = {
			"{ $lookup:{ from : 'catalogos', localField: 'unidadMedida', foreignField: 'codigo', as: 'um'}}, ",
			"{ $lookup:{ from : 'catalogos', localField: 'codigoAlmacen', foreignField: 'codigo', as: 'almacen'}},",
		    "{ $match: { 'um.tipo' : '1', 'almacen.tipo' : '2',  'idIngreso' : ?0}} "
			
	})
	Flux<Mercaderia> findByIngreso(String idIngreso);
	
	
	@Query("{'numeroMercaderia': ?0 }")
	Mono<Mercaderia> findByNumeroMercaderia(String numeroMercaderia);
}
