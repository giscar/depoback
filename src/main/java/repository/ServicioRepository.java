package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Montacarga;
import model.Servicio;
import reactor.core.publisher.Flux;

public interface ServicioRepository extends ReactiveMongoRepository<Servicio, String> {
	
	@Query("{'estado': '1'})")
	Flux<Servicio> findByEstado(String estado);
	
	@Query("{$or: [{'ruc': ?0}, {'codServicio': ?1}] }")
	Flux<Servicio> findByRucCodServicio(String ruc, String estado);
	
	@Aggregation(pipeline = {
			"{ $addFields: { 'operadorObjId': { '$toObjectId': '$operadorId' }}},",
			"{ $lookup:{ from : 'operadores', localField: 'operadorObjId', foreignField: '_id', as: 'innerOperadores'}},",
			//"{ $replaceRoot: { newRoot: { $mergeObjects: [{$arrayElemAt: ['$innerOperadores', 0]}, '$$ROOT']}} }"
			
	})
	Flux<Servicio> findByRucCodServicioAggregate(String ruc, String codServicio);
}
