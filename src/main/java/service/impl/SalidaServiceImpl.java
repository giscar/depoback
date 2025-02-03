package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Salida;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.SalidaRepository;
import service.SalidaService;

@Service
public class SalidaServiceImpl implements SalidaService{
	
	@Autowired
	private SalidaRepository salidaRepository;

	@Override
	public Mono<Salida> save(Salida salida) {
		return salidaRepository.save(salida);
	}

	@Override
	public Mono<Salida> edit(Salida salida) {
		return salidaRepository.save(salida);
	}

	@Override
	public Mono<Salida> salidaByID(String id) {
		return salidaRepository.findById(id);
	}

	@Override
	public Flux<Salida> findByNumeroMercaderia(String numeroMercaderia) {
		return salidaRepository.findByNumeroMercaderia(numeroMercaderia);
	}

	


	

}
