package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Montacarga;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.MontacargaRepository;
import service.MontacargaService;

@Service
public class MontacargaServiceImpl implements MontacargaService{
	
	@Autowired
	private MontacargaRepository montacargaRepository;

	@Override
	public Mono<Montacarga> save(Montacarga montacarga) {
		return montacargaRepository.save(montacarga);
	}
	
	public Mono<Montacarga> inactivar(Montacarga montacarga) {
		return montacargaRepository.save(montacarga);
	}

	@Override
	public Mono<Montacarga> findById(String id) {
		return montacargaRepository.findById(id);
	}

	@Override
	public Flux<Montacarga> all() {
		return montacargaRepository.findAll();
	}

	@Override
	public Flux<Montacarga> findByEstado() {
		return montacargaRepository.findByEstado();
	}

	@Override
	public Mono<Montacarga> edit(Montacarga montacarga) {
		return montacargaRepository.findById(montacarga.getId()).map(p -> {
			p.setEstadoRegistro("0");
			return p;
		}).doOnNext(q -> {
			montacargaRepository.save(q).subscribe();
		}).doOnNext(p -> {
			montacarga.setId(null);
			montacarga.setEstadoRegistro("1");
			montacargaRepository.save(montacarga).subscribe();
		});
	}

	@Override
	public Mono<Montacarga> inactiva(Montacarga montacarga) {
		return montacargaRepository.findById(montacarga.getId()).map(p -> {
			p.setEstadoRegistro("0");
			return p;
		}).doOnNext(q -> {
			montacargaRepository.save(q).subscribe();
		}).doOnNext(p -> {
			montacarga.setId(null);
			montacarga.setEstadoRegistro("1");
			montacarga.setIndInactivo("1");
			montacargaRepository.save(montacarga).subscribe();
		});
	}
	
	

}
