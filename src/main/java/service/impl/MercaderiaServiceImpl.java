package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Mercaderia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.MercaderiaRepository;
import service.MercaderiaService;

@Service
public class MercaderiaServiceImpl implements MercaderiaService{
	
	@Autowired
	private MercaderiaRepository mercaderiaRepository;

	@Override
	public Flux<Mercaderia> mercaderiaByRuc(String ruc) {
		return mercaderiaRepository.findAll();
	}

	@Override
	public Mono<Mercaderia> save(Mercaderia mercaderia) {
		return mercaderiaRepository.save(mercaderia);
	}

	@Override
	public Mono<Mercaderia> edit(Mercaderia mercaderia) {
		return mercaderiaRepository.save(mercaderia);
	}

	@Override
	public Mono<Mercaderia> mercaderiaByID(String id) {
		return mercaderiaRepository.findById(id);
	}

	@Override
	public Mono<Object> findMaxCodMercaderia() {
		return mercaderiaRepository.findMaxCodMercaderia();
	}

	@Override
	public Flux<Mercaderia> findByIngreso(String idIngreso) {
		return mercaderiaRepository.findByIngreso(idIngreso);
	}

	@Override
	public Mono<Mercaderia> findByNumeroMercaderia(String numeroMercaderia) {
		return mercaderiaRepository.findByNumeroMercaderia(numeroMercaderia);
	}


	

}
