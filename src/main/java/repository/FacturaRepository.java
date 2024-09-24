package repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FacturaRepository extends ReactiveMongoRepository<Factura, String> {
	
	Flux<Factura> findByRuc(String ruc);
	
	Mono<Factura> findById(String id);
	
	@Aggregation(pipeline = {
			"{$group: { _id: null, maxField : { $max: '$codigoFactura' }}}"
	})
	Mono<Object> findMaxCodFactura();

}
