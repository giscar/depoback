package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Perfil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerfilRepository extends ReactiveMongoRepository<Perfil, String> {
	

	@Aggregation(pipeline = {
			"{ $addFields: { 'stringId': { '$toString': '$_id' },}}, ",
			"{ $lookup:{ from : 'roles', localField: 'roles._id', foreignField: '_id', as: 'roles'}}, ",
			"{ $match: { 'estado' : '1' }}	"
	})
	Flux<Perfil> findByEstado();
	
	@Query("{'estado': '1', 'codigo' :  ?0 }")	
	Mono<Perfil> findByEstadoByCodigo(String codigo);
	
	@Aggregation(pipeline = {
			"{ $addFields: { 'stringId': { '$toString': '$_id' },}}, ",
			"{ $lookup:{ from : 'roles', localField: 'roles._id', foreignField: '_id', as: 'inner'}}, ",
			"{ $match: { 'stringId' : 0? }}	",
			"{ $project: {'_class' : 0, 'stringId' : 0 }}"
	})
	Mono<Perfil> findByIdAggregate(String id);
}
