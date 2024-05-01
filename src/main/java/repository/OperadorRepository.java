package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Cliente;
import model.Montacarga;
import model.Operador;
import reactor.core.publisher.Flux;

public interface OperadorRepository extends ReactiveMongoRepository<Operador, String> {
	

}
