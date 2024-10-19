package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.RolRepository;
import service.RolService;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public Flux<Rol> all() {
		return rolRepository.findAll();
	}

	@Override
	public Mono<Rol> save(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	public Mono<Rol> findById(String id) {
		return rolRepository.findById(id);
	}

	@Override
	public Flux<Rol> findByEstado() {
		return rolRepository.findByEstado();
	}

	@Override
	public Mono<Rol> findByEstadoByCodigo(String codigo) {
		return rolRepository.findByEstadoByCodigo(codigo);
	}

	@Override
	public Mono<Rol> edit(Rol rol) {
		return rolRepository.findById(rol.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			rolRepository.save(q).subscribe();
		}).doOnNext(p -> {
			rol.setId(null);
			rol.setEstado("1");
			rolRepository.save(rol).subscribe();
		});
	}

	@Override
	public Mono<Rol> inactiva(Rol rol) {
		return rolRepository.findById(rol.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			rolRepository.save(q).subscribe();
		}).doOnNext(p -> {
			rol.setId(null);
			rol.setEstado("0");
			rol.setIndInactivo("1");
			rolRepository.save(rol).subscribe();
		});
	}	

}
