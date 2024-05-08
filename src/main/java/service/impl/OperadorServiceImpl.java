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
	public Flux<Operador> findByEstado(String estado) {
		return operadorRepository.findByEstado(estado);
	}

	
	
	

}
