package service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Operador;
import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ServicioRepository;
import service.OperadorService;
import service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService{
	
	Logger logger = LoggerFactory.getLogger(ServicioServiceImpl.class);
	

	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private OperadorService operadorService;

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

	@Override
	public Flux<Servicio> findByRucCodServicio(String ruc, String codServicio) {
		return servicioRepository.findByRucCodServicio(ruc, codServicio);
	}

	@Override
	public Flux<Servicio> findByRucCodServicioAggregate(String ruc, String codServicio) {
		return servicioRepository.findByRucCodServicioAggregate(ruc, codServicio).log();
		/*return servicioRepository.findByRucCodServicioAggregate(ruc, codServicio).
				map(p -> {
					operadorService.findById(p.getOperadorId()).map(q -> {
						ArrayList<Operador> listaOperador = new ArrayList<>();
						listaOperador.add(q);
						p.setOperador(listaOperador);
						return q;
					});
					return p;
				});*/
				
		/*return servicioRepository.findByRucCodServicioAggregate(ruc, codServicio)
				.flatMap(p -> {
					operadorService.findById(p.getOperadorId()).flatMap(q -> {
						logger.info("MENSAJE DE PRUEBA LOG");
						p.setOperador(q);
						return Mono.just(q);
					});
					return Flux.just(p);
				});*/
	}
	
	@Override
	public Mono<Servicio> findByIdAggregate(String id) {
		return servicioRepository.findByIdAggregate(id).log();
	}
	
	

}
