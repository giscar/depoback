package service;

import model.FormularioContacto;
import reactor.core.publisher.Mono;

public interface FormularioContactoService {
	
	public Mono<FormularioContacto> save(FormularioContacto formularioContacto);
	
}
