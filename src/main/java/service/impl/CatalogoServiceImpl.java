package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Catalogo;
import reactor.core.publisher.Flux;
import repository.CatalogoRepository;
import service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService{
	
	@Autowired
	private CatalogoRepository catalogoRepository;

	@Override
	public Flux<Catalogo> findByTipo(String tipo) {
		return catalogoRepository.findByTipo(tipo);
	}

	

}
