package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Ingreso;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngresoRepository extends ReactiveMongoRepository<Ingreso, String> {
	
	@Aggregation(pipeline = {
			"{$group: { _id: null, maxField : { $max: '$codIngreso' }}}"
	})
	Mono<Object> findMaxCodServicio();
	
	@Aggregation(pipeline = {
			"{ $match:{ $or :[ {'pedidoDeposito' : ?0},{'codigoDua' : ?1},{'ruc' : ?2},{'tipoMercaderia' : ?3} ]}},"
			//"{ $match:{ $or :[{'ruc' : ?0},{'codServicio' : ?1},{'operadorId' : ?2},{'montacargaId' : ?3},{'estadoRegistro' : ?4},{'tipoServicio' : ?5} ]}},"
			
	})
	Flux<Ingreso> findByFiltrer(String pedidoDeposito, String codigoDua, String ruc, String tipoMercaderia);
	
	
}
