package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Cliente;
import model.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteRepository extends ReactiveMongoRepository<Cliente, String> {
	
	Flux<Cliente> findByRuc(String ruc);

}
