package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Perfil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.PerfilRepository;
import repository.RolRepository;
import service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public Flux<Perfil> all() {
		return perfilRepository.findAll();
	}

	@Override
	public Mono<Perfil> save(Perfil perfil) {
		return perfilRepository.save(perfil);
	}

	@Override
	public Mono<Perfil> findById(String id) {
		return perfilRepository.findById(id);
	}

	@Override
	public Flux<Perfil> findByEstado() {
		return perfilRepository.findByEstado();
	}

	@Override
	public Mono<Perfil> findByEstadoByCodigo(String documento) {
		return perfilRepository.findByEstadoByCodigo(documento);
	}

	@Override
	public Mono<Perfil> edit(Perfil perfil) {
		return perfilRepository.findById(perfil.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			perfilRepository.save(q).subscribe();
		}).doOnNext(p -> {
			perfil.setId(null);
			perfil.setEstado("1");
			perfilRepository.save(perfil).subscribe();
		});
	}

	@Override
	public Mono<Perfil> inactiva(Perfil perfil) {
		return perfilRepository.findById(perfil.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			perfilRepository.save(q).subscribe();
		}).doOnNext(p -> {
			perfil.setId(null);
			perfil.setEstado("0");
			perfil.setIndInactivo("1");
			perfilRepository.save(perfil).subscribe();
		});
	}

	@Override
	public Mono<Perfil> findByIdAggregate(String id) {
		//Mono<Perfil> mono = perfilRepository.findByIdAggregate(id); 
		Mono<Perfil> mono = perfilRepository.findById(id); 

		return mono.map(p -> {
			return p;
		});
	}	

}
