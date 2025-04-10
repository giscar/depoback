package service;

import model.NotaRecepcion;
import reactor.core.publisher.Mono;

public interface NotaRecepcionService {
	
	Mono<NotaRecepcion> saveNotaRecepcion(NotaRecepcion notaRecepcion);
	
	Mono<NotaRecepcion> findNotaRecepcionById(String id);
	
	Mono<Object> findMaxNumeroRecepcion();
	
	Mono<NotaRecepcion> findByCodIngreso(String idIngreso);

}
