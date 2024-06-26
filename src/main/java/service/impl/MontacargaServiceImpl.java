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
	
	

}
