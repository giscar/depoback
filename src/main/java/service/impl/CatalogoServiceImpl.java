package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Catalogo;
import model.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.CatalogoRepository;
import repository.RolRepository;
import service.CatalogoService;
import service.RolService;

@Service
public class CatalogoServiceImpl implements CatalogoService{
	
	@Autowired
	private CatalogoRepository catalogoRepository;

	@Override
	public Flux<Catalogo> findByTipo(String tipo) {
		return catalogoRepository.findByTipo(tipo);
	}

	

}
