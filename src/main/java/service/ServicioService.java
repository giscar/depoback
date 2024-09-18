package service;

import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServicioService {
	
	Mono<Servicio> save(Servicio servicio);
	
	Mono<Servicio> findById(String id);
	
	Flux<Servicio> findByEstado(String estado);
	
	Flux<Servicio> all();
	
	Flux<Servicio> findByRucCodServicio(String ruc, Integer codServicio);
	
	Flux<Servicio> findByRucCodServicioAggregate(String ruc, Integer codServicio);
	
	Flux<Servicio> findByRucEstadisticasAggregate(String ruc, Integer codServicio, String idOperador, String idMontacarga, String estadoRegistro, String tipoServicio);
	
	Flux<Servicio> findByOperadorIdAggregate(String idOperador);
	
	Flux<Servicio> findByServiciosPendientes();
	
	Flux<Servicio> findByServiciosConcluidos();
	
	Mono<Servicio> findByIdAggregate(String id);
	
	Mono<Object> findMaxCodServicio();

}
