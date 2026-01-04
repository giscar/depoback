package controller;

import domain.type.EstadoRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Montacarga;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.MontacargaService;

@RestController
@RequestMapping("/montacargas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class MontacargaController {

	private final MontacargaService service;

	// ============================
	// GET by ID
	// ============================
	@GetMapping("/{id}")
	public Mono<Montacarga> obtenerPorId(@PathVariable String id) {
		log.info("GET /montacargas/{}", id);
		return service.obtenerPorId(id)
				.doOnSuccess(m ->
						log.info("Montacarga encontrada id={}", m.getId()))
				.doOnError(e ->
						log.error("Error al obtener montacarga id={}", id, e));
	}

	// ============================
	// GET by estado
	// ============================
	@GetMapping
	public Flux<Montacarga> obtenerPorEstado(
			@RequestParam(name = "estado", required = false) EstadoRegister estado) {

		log.info("GET /montacargas?estado={}", estado);

		if (estado == null) {
			return service.obtenerTodos()
					.doOnComplete(() ->
							log.info("Listado completo de montacargas"));
		}

		return service.obtenerPorEstado(estado)
				.doOnComplete(() ->
						log.info("Listado de montacargas por estado={}", estado))
				.doOnError(e ->
						log.error("Error al listar montacargas por estado={}", estado, e));
	}

	// ============================
	// POST crear
	// ============================
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Montacarga> crear(@RequestBody Montacarga montacarga) {
		log.info("POST /montacargas - crear montacarga");

		return service.crear(montacarga)
				.doOnSuccess(m ->
						log.info("Montacarga creada id={}", m.getId()))
				.doOnError(e ->
						log.error("Error al crear montacarga", e));
	}

	// ============================
	// PUT actualizar
	// ============================
	@PutMapping("/{id}")
	public Mono<Montacarga> actualizar(
			@PathVariable String id,
			@RequestBody Montacarga montacarga) {

		log.info("PUT /montacargas/{}", id);

		return service.actualizar(id, montacarga)
				.doOnSuccess(m ->
						log.info("Montacarga actualizada id={}", m.getId()))
				.doOnError(e ->
						log.error("Error al actualizar montacarga id={}", id, e));
	}

	// ============================
	// PUT inactivar (soft delete)
	// ============================
	@PutMapping("/{id}/inactivar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> inactivar(@PathVariable String id) {
		log.info("PUT /montacargas/{}/inactivar", id);

		return service.inactivar(id)
				.doOnSuccess(v ->
						log.info("Montacarga inactivada id={}", id))
				.doOnError(e ->
						log.error("Error al inactivar montacarga id={}", id, e));
	}
}
