package service;

import domain.type.EstadoRegister;
import model.Montacarga;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MontacargaService {

	Mono<Montacarga> crear(Montacarga montacarga);

	Mono<Montacarga> actualizar(String id, Montacarga montacarga);

	Mono<Void> inactivar(String id);

	Mono<Montacarga> obtenerPorId(String id);

	Flux<Montacarga> obtenerPorEstado(EstadoRegister estadoRegister);

	Flux<Montacarga> obtenerTodos();

}
