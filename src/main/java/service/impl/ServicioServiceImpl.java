package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ServicioRepository;
import service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService{
	
	@Autowired
	private ServicioRepository servicioRepository;

	@Override
	public Mono<Servicio> save(Servicio servicio) {
		return servicioRepository.save(servicio);
	}

	@Override
	public Mono<Servicio> findById(String id) {
		return servicioRepository.findById(id);
	}

	@Override
	public Flux<Servicio> all() {
		return servicioRepository.findAll();
	}

	@Override
	public Flux<Servicio> findByEstado(String estado) {
		return servicioRepository.findByEstado(estado);
	}
	
	

}