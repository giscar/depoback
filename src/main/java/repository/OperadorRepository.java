package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Operador;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperadorRepository extends ReactiveMongoRepository<Operador, String> {
	
	
	@Aggregation(pipeline = {
			"{ $match:{ 'estado': '1'} },",
			"{ $sort:{ 'nombre': 1} },"
	})
	Flux<Operador> findByEstado();
	
	@Query("{'estado': '1', 'documento' :  ?0 }")
	Mono<Operador> findByEstadoByDocumento(String documento);
}
