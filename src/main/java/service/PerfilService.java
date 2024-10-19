package service;

import model.Perfil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerfilService {
	
	Flux<Perfil> all();
	
	Mono<Perfil> save(Perfil perfil);
	
	Mono<Perfil> edit(Perfil perfil);
	
	Mono<Perfil> inactiva(Perfil perfil);
	
	Mono<Perfil> findById(String id);
	
	Mono<Perfil> findByEstadoByCodigo(String codigo);
	
	Flux<Perfil> findByEstado();

}
