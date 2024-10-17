package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Operador;
import model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.OperadorRepository;
import repository.UsuarioRepository;
import service.OperadorService;
import service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Flux<Usuario> all() {
		return usuarioRepository.findAll();
	}

	@Override
	public Mono<Usuario> save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Mono<Usuario> findById(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Flux<Usuario> findByEstado() {
		return usuarioRepository.findByEstado();
	}

	@Override
	public Mono<Usuario> findByEstadoByDocumento(String documento) {
		return usuarioRepository.findByEstadoByDocumento(documento);
	}

	@Override
	public Mono<Usuario> edit(Usuario usuario) {
		return usuarioRepository.findById(usuario.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			usuarioRepository.save(q).subscribe();
		}).doOnNext(p -> {
			usuario.setId(null);
			usuario.setEstado("1");
			usuarioRepository.save(usuario).subscribe();
		});
	}

	@Override
	public Mono<Usuario> inactiva(Usuario usuario) {
		return usuarioRepository.findById(usuario.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			usuarioRepository.save(q).subscribe();
		}).doOnNext(p -> {
			usuario.setId(null);
			usuario.setEstado("0");
			usuario.setIndInactivo("1");
			usuarioRepository.save(usuario).subscribe();
		});
	}	

}
