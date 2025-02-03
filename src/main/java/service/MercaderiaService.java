package service;

import model.Mercaderia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MercaderiaService {
	
	Flux<Mercaderia> mercaderiaByRuc(String ruc);
	
	Mono<Mercaderia> save(Mercaderia mercaderia);
	
	Mono<Mercaderia> edit(Mercaderia mercaderia);
	
	Mono<Mercaderia> mercaderiaByID(String id);
	
	Mono<Object> findMaxCodMercaderia();
	
	Flux<Mercaderia> findByIngreso(String idIngreso);

}
