package repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Cliente;
import reactor.core.publisher.Flux;

public interface ClienteRepository extends ReactiveMongoRepository<Cliente, String> {
	
	Flux<Cliente> findByRuc(String ruc);
	
	@Query("{'razonSocial': {$regex: ?0 }})")
	Flux<Cliente> clienteForDescripcion(String descripcion);

}
