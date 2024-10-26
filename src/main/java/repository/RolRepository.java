package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Perfil;
import model.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolRepository extends ReactiveMongoRepository<Rol, String> {
	

	@Query("{'estado': '1'}")
	Flux<Rol> findByEstado();
	
	@Query("{'estado': '1', 'codigo' :  ?0 }")
	Mono<Rol> findByEstadoByCodigo(String codigo);
	
	@Aggregation(pipeline = {
			"{ $addFields: { 'stringId': { '$toString': '$_id' },}}, ",
			"{ $match: { 'stringId' : 0? }}	",
	})
	Rol findByEstadoByCodigoObject(String id);
}
