package service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ServicioRepository;
import service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService{
	
	Logger logger = LoggerFactory.getLogger(ServicioServiceImpl.class);
	

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

	@Override
	public Flux<Servicio> findByRucCodServicio(String ruc, Integer codServicio) {
		return servicioRepository.findByRucCodServicio(ruc, codServicio);
	}

	@Override
	public Flux<Servicio> findByRucCodServicioAggregate(String ruc, Integer codServicio) {
		return servicioRepository.findByRucCodServicioAggregate(ruc, codServicio);
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
	
	@Override
	public Mono<Object> findMaxCodServicio(){
		return servicioRepository.findMaxCodServicio();
	}

	@Override
	public Flux<Servicio> findByOperadorIdAggregate(String documento) {
		return servicioRepository.findByOperadorIdAggregate(documento);
	}

	@Override
	public Flux<Servicio> findByServiciosPendientes() {
		return servicioRepository.findByServiciosPendientes();
	}
	
	@Override
	public Flux<Servicio> findByServiciosConcluidos() {
		return servicioRepository.findByServiciosConcluidos();
	}

	@Override
	public Flux<Servicio> findByRucEstadisticasAggregate(String ruc, Integer codServicio, String idOperador,
			String idMontacarga, String estadoRegistro, String tipoServicio) {
		return servicioRepository.findByRucEstadisticasAggregate(ruc, codServicio, idOperador, idMontacarga, estadoRegistro, tipoServicio);
	}

	@Override
	public Flux<Servicio> findByServiciosConcluidosInServicios(String[] idServicios) {
		return Flux.just(idServicios).flatMap(p -> {
			return Flux.concat(servicioRepository.findByServiciosConcluidosInServicios(Long.parseLong(p)));
		}); 
	}

	@Override
	public Flux<Servicio> findByRucCodServicioAggregateConcluido(String ruc, Integer codServicio) {
		return servicioRepository.findByRucCodServicioAggregateConcluido(ruc, codServicio);
	}

	@Override
	public Mono<Servicio> facturarServicio(Servicio servicio) {
		servicio.setEstadoRegistro("Facturado");
		servicio.setFechaFacturacion(new Date());
		return servicioRepository.save(servicio);
	}

	@Override
	public Mono<Servicio> findByCodigoServicio(Long codServicio) {
		return servicioRepository.findByCodigoServicio(codServicio);
	}

}
