package service;

import model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {
	
	Flux<Usuario> all();
	
	Mono<Usuario> save(Usuario usuario);
	
	Mono<Usuario> edit(Usuario usuario);
	
	Mono<Usuario> inactiva(Usuario usuario);
	
	Mono<Usuario> findById(String id);
	
	Mono<Usuario> findByEstadoByDocumento(String documento);
	
	Flux<Usuario> findByEstado();

}
