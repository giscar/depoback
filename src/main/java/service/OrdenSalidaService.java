package service;

import model.OrdenSalida;
import reactor.core.publisher.Mono;

public interface OrdenSalidaService {
	
	Mono<OrdenSalida> saveOrdenSalida(OrdenSalida ordenSalida);
	
	Mono<OrdenSalida> findOrdenSalidaById(String id);

}
