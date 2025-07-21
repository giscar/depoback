package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Factura;
import model.FormularioContacto;
import reactor.core.publisher.Mono;
import repository.FormularioContactoRepository;
import service.FormularioContactoService;

@Service
public class FormularioContactoServiceImpl implements FormularioContactoService{
	
	@Autowired
	private FormularioContactoRepository formularioContactoRepository;


	@Override
	public Mono<FormularioContacto> save(FormularioContacto formularioContacto) {
		return formularioContactoRepository.save(formularioContacto);
	}

}
