package service;

import model.OrdenSalida;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdenSalidaService {
	
	Mono<OrdenSalida> saveOrdenSalida(OrdenSalida ordenSalida);
	
	Mono<OrdenSalida> findOrdenSalidaById(String id);
	
	Mono<Object> findMaxOrdenSalida();
	
	Flux<OrdenSalida> findByCodIngreso(String idIngreso);

}
