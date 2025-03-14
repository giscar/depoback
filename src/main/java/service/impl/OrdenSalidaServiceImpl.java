package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.OrdenSalida;
import reactor.core.publisher.Mono;
import repository.OrdenSalidaRepository;
import service.OrdenSalidaService;

@Service
public class OrdenSalidaServiceImpl implements OrdenSalidaService{
	
	@Autowired
	private OrdenSalidaRepository ordenSalidaRepository;

	@Override
	public Mono<OrdenSalida> saveOrdenSalida(OrdenSalida ordenSalida) {
		// TODO Auto-generated method stub
		return ordenSalidaRepository.save(ordenSalida);
	}

	@Override
	public Mono<OrdenSalida> findOrdenSalidaById(String id) {
		return ordenSalidaRepository.findById(id);
	}

	@Override
	public Mono<Object> findMaxOrdenSalida() {
		// TODO Auto-generated method stub
		return ordenSalidaRepository.findMaxOrdenSalida();
	}

	

	

}
