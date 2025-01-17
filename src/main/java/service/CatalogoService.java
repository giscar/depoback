package service;

import model.Catalogo;
import reactor.core.publisher.Flux;

public interface CatalogoService {
	
	Flux<Catalogo> findByTipo(String tipo);
	

}
