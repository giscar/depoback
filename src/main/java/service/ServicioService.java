package service;

import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServicioService {
	
	Mono<Servicio> save(Servicio servicio);
	
	Mono<Servicio> findById(String id);
	
	Flux<Servicio> findByEstado(String estado);
	
	Flux<Servicio> all();
	
	Flux<Servicio> findByRucCodServicio(String ruc, String codServicio);
	
	Flux<Servicio> findByRucCodServicioAggregate(String ruc, String codServicio);
	
	Mono<Servicio> findByIdAggregate(String id);
	
	Mono<Object> findMaxCodServicio();

}
