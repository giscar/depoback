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
	
	@Query("{'estado': '1', 'documento' :  ?0 }")
	Mono<Usuario> findByEstadoByDocumento(String documento);
}
