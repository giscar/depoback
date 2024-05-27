package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Operador;
import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.OperadorRepository;
import repository.ServicioRepository;
import service.OperadorService;
import service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService{
	
	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private OperadorService operadorService;

	@Override
	public Mono<Servicio> save(Servicio servicio) {
		return Mono.just(servicio).flatMap(p -> {
			operadorService.findById(p.getOperadorId()).map(q -> {
				System.out.println(q);
			p.setOperador(q);	
				return Mono.just(q);
			});		
			return servicioRepository.save(p);
		});
				
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

	@Override
	public Flux<Servicio> findByRucCodServicio(String ruc, String codServicio) {
		return servicioRepository.findByRucCodServicio(ruc, codServicio);
	}

	@Override
	public Flux<Servicio> findByRucCodServicioAggregate(String ruc, String codServicio) {
		return servicioRepository.findByRucCodServicioAggregate(ruc, codServicio)
				.flatMap(p -> {
					operadorService.findById(p.getOperadorId()).flatMap(q -> {
						p.setOperador(q);
						return Mono.just(q);
					});
					System.out.print(p);
					return Flux.just(p);
				});
	}
	
	

}
