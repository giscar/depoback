package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveMongoRepository<Usuario, String> {
	

	//@Query("{'estado': '1'}")
	//Flux<Usuario> findByEstado();
	
	@Aggregation(pipeline = {
			"{ $addFields: { 'stringId': { '$toString': '$_id' },}}, ",
			"{ $lookup:{ from : 'perfiles', localField: 'perfiles._id', foreignField: '_id', as: 'perfiles'}}, ",
			"{ $match: { 'estado' : '1' }}	"
	})
	Flux<Usuario> findByEstado();
	
	@Aggregation(pipeline = {
			"{ $lookup:{ from : 'perfiles', localField: 'perfiles._id', foreignField: '_id', as: 'perfiles'}}, ",
			//"{ $lookup:{ from : 'roles', localField: 'perfiles.roles._id', foreignField: '_id', as: 'perfiles.roles'}}, ",
			"{ $match: { 'estado' : '1' , 'documento' : ?0, 'passwd' : ?1 }} "
	})
	Mono<Usuario> findByRolesForUser(String documento, String passwd);
	
	@Aggregation(pipeline = {
			"{ $lookup:{ from : 'perfiles', localField: 'perfiles._id', foreignField: '_id', as: 'perfiles'}}, ",
			"{ $lookup:{ from : 'roles', localField: 'perfiles.roles._id', foreignField: '_id', as: 'perfiles.roles'}}, ",
			"{ $match: { 'estado' : '1' , 'documento' : '4343443' }} "
	})
	Mono<Usuario> findByRolesForUser2(String documento);
	
	
	
	@Query("{'estado': '1', 'documento' :  ?0 }")
	Mono<Usuario> findByEstadoByDocumento(String documento);
}
