package service.impl;

import domain.type.EstadoRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import model.Montacarga;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.MontacargaRepository;
import service.MontacargaService;

@Service
@RequiredArgsConstructor
@Slf4j
public class MontacargaServiceImpl implements MontacargaService{

	private final MontacargaRepository repository;

	@Override
	public Mono<Montacarga> crear(Montacarga montacarga) {

		return Mono.justOrEmpty(montacarga)
				.doOnSubscribe(s -> log.info("Inicia la creacion de la montacarga"))
				.switchIfEmpty(Mono.error(
						new IllegalArgumentException("La montacarga no puede ser null")
				))
				.doOnNext(p -> {
					p.setEstadoRegistro(EstadoRegister.ACTIVO);
				})
				.flatMap(repository::save)
				.doOnSuccess(m -> log.info("Montacarga creada correctamete con el id {}", m.getId()))
				.doOnError(e -> log.error("Error en registrar la montacarga {}", e.getMessage(), e));
	}
	
	public Mono<Void> inactivar(String id) {

		return repository.findById(id)
				.doOnSubscribe(l -> log.info("Inicia la inactivacion de la montacarga id = {}", id))
				.switchIfEmpty(Mono.error(new IllegalArgumentException("No existe la montacarga con el id {}"+ id)))
				.flatMap(p -> {
					log.debug("Inactivando la montacarga id: {}", p.getId());
					p.setEstadoRegistro(EstadoRegister.INACTIVO);
					return repository.save(p);
				})
				.doOnSuccess(s -> log.info("se inactivo la montacarga con id: {}", s.getId()))
				.doOnError(e -> log.error("Error en la inactivacion de la montacarga id = {} : {}", id, e.getMessage(), e))
				.then();
	}

	@Override
	public Mono<Montacarga> obtenerPorId(String id) {

		return repository.findById(id)
				.doOnSubscribe(s -> log.info("Obtener la montacarga por id: {}", id))
				.switchIfEmpty(Mono.error(new IllegalArgumentException("No se enconntro la montacarga con id :" + id)))
				.doOnSuccess(s -> log.info("Se encontro la montacarga con el id: {}", id))
				.doOnError(e -> log.error("error en encontrar la monntacarga con el id : {}", id));
	}

	@Override
	public Flux<Montacarga> obtenerTodos() {
		return repository.findAll()
				.doOnSubscribe(s ->
						log.debug("Consultando todos los montacargas"))
				.doOnComplete(() ->
						log.info("Consulta de todos los montacargas finalizada"))
				.doOnError(e ->
						log.error("Error al consultar todos los montacargas: {}", e.getMessage(), e));
	}

	@Override
	public Flux<Montacarga> obtenerPorEstado(EstadoRegister estadoRegister) {
		return Mono.justOrEmpty(estadoRegister)
				.switchIfEmpty(Mono.error(new IllegalArgumentException("el estado registro es necesario")))
				.flatMapMany(p -> repository.findByEstadoRegistro(p.name()))
				.doOnComplete(() -> log.info("se encontraron las montacargaspor el estado: {}", estadoRegister))
				.doOnError(e -> log.error("error en encotrar las montacargas con el estado {} ", estadoRegister));
	}


	@Override
	public Mono<Montacarga> actualizar(String id, Montacarga montacarga) {
		return Mono.justOrEmpty(montacarga)
				.doOnSubscribe(s -> log.info("Se va a actualizar la monntacargar id : {}", id))
				.switchIfEmpty(Mono.error(new IllegalArgumentException("esta vacio la monntacarga con id :"+id)))
						.flatMap(p ->
							repository.findById(id)
							.switchIfEmpty(Mono.error(new IllegalArgumentException("No existe informacion de la monntacrga con id: "+ id)))
									.flatMap(d -> {
										log.debug("Actualizando montacarga con id: {}", d.getId());
										return repository.save(d);
									})
						)
				.doOnSuccess(m -> log.info("Se actualizo la montacarga con id : {}", m.getId()))
				.doOnError(e -> log.error("Error enn actualizacion de la montacarga con id: {}, {}", id, e.getMessage(), e));
	}

}
