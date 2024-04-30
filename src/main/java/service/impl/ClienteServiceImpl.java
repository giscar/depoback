package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Cliente;
import model.Factura;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ClienteRepository;
import repository.FacturaRepository;
import service.ClienteService;
import service.FacturaService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;


	@Override
	public Flux<Cliente> clienteForRuc(String ruc) {
		return clienteRepository.findByRuc(ruc);
	}


	@Override
	public Flux<Cliente> clienteForDescripcion(String descripcion) {
		return clienteRepository.clienteForDescripcion(descripcion.toUpperCase());
	}


	@Override
	public Mono<Cliente> setCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}


	@Override
	public Mono<Cliente> clienteForID(String id) {
		return clienteRepository.findById(id);
	}

}
