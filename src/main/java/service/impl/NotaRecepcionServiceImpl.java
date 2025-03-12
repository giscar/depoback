package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.NotaRecepcion;
import reactor.core.publisher.Mono;
import repository.NotaRecepcionRepository;
import service.NotaRecepcionService;

@Service
public class NotaRecepcionServiceImpl implements NotaRecepcionService{
	
	@Autowired
	private NotaRecepcionRepository notaRecepcionRepository;

	@Override
	public Mono<NotaRecepcion> saveNotaRecepcion(NotaRecepcion notaRecepcion) {
		return notaRecepcionRepository.save(notaRecepcion);
	}

	@Override
	public Mono<NotaRecepcion> findNotaRecepcionById(String id) {
		return notaRecepcionRepository.findById(id);
	}

	@Override
	public Mono<Object> findMaxNumeroRecepcion() {
		return notaRecepcionRepository.findMaxNumeroRecepcion();
	}

	

}
