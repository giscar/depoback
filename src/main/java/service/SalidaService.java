package service;

import model.Salida;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalidaService {
	
	Mono<Salida> save(Salida salida);
	
	Mono<Salida> edit(Salida salida);
	
	Mono<Salida> salidaByID(String id);
	
	Flux<Salida> findByNumeroMercaderia(String numeroMercaderia);
	
	Flux<Salida> findByIngreso(String idIngreso);

}
