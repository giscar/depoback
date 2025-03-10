package service;

import model.NotaRecepcion;
import reactor.core.publisher.Mono;

public interface NotaRecepcionService {
	
	Mono<NotaRecepcion> saveNotaRecepcion(NotaRecepcion notaRecepcion);
	
	Mono<NotaRecepcion> findNotaRecepcionById(String id);

}
