package service;

import model.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolService {
	
	Flux<Rol> all();
	
	Mono<Rol> save(Rol Rol);
	
	Mono<Rol> edit(Rol Rol);
	
	Mono<Rol> inactiva(Rol Rol);
	
	Mono<Rol> findById(String id);
	
	Mono<Rol> findByEstadoByCodigo(String codigo);
	
	Flux<Rol> findByEstado();

}
