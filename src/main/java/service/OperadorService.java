package service;

import model.Operador;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperadorService {
	
	Flux<Operador> all();
	
	Mono<Operador> save(Operador operador);
	
	Mono<Operador> findById(String id);
	
	Flux<Operador> findByEstado();

}
