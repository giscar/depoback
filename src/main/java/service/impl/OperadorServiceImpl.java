package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Operador;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.OperadorRepository;
import service.OperadorService;

@Service
public class OperadorServiceImpl implements OperadorService{
	
	@Autowired
	private OperadorRepository operadorRepository;

	@Override
	public Flux<Operador> all() {
		return operadorRepository.findAll();
	}

	@Override
	public Mono<Operador> save(Operador operador) {
		return operadorRepository.save(operador);
	}

	@Override
	public Mono<Operador> findById(String id) {
		return operadorRepository.findById(id);
	}

	@Override
	public Flux<Operador> findByEstado() {
		return operadorRepository.findByEstado();
	}

	@Override
	public Mono<Operador> findByEstadoByDocumento(String documento) {
		return operadorRepository.findByEstadoByDocumento(documento);
	}

	@Override
	public Mono<Operador> edit(Operador operador) {
		return operadorRepository.findById(operador.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			operadorRepository.save(q).subscribe();
		}).doOnNext(p -> {
			operador.setId(null);
			operador.setEstado("1");
			operadorRepository.save(operador).subscribe();
		});
	}

	@Override
	public Mono<Operador> inactiva(Operador operador) {
		return operadorRepository.findById(operador.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			operadorRepository.save(q).subscribe();
		}).doOnNext(p -> {
			operador.setId(null);
			operador.setEstado("0");
			operador.setIndInactivo("1");
			operadorRepository.save(operador).subscribe();
		});
	}	

}
