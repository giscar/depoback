package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FacturaRepository extends ReactiveMongoRepository<Factura, String> {
	
	Flux<Factura> findByRuc(String ruc);
	
	Mono<Factura> findById(String id);

}
