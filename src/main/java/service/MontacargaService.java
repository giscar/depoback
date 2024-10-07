package service;

import model.Montacarga;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MontacargaService {
	
	Mono<Montacarga> save(Montacarga montacarga);
	
	Mono<Montacarga> edit(Montacarga montacarga);
	
	Mono<Montacarga> inactiva(Montacarga montacarga);
	
	Mono<Montacarga> findById(String id);
	
	Flux<Montacarga> findByEstado();
	
	Flux<Montacarga> all();

}
