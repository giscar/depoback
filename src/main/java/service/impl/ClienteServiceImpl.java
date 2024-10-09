package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Cliente;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ClienteRepository;
import service.ClienteService;

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
	public Mono<Cliente> save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Override
	public Mono<Cliente> edit(Cliente cliente) {
		return clienteRepository.findById(cliente.getId()).map(p -> {
			p.setEstado("0");
			return p;
		}).doOnNext(q -> {
			clienteRepository.save(q).subscribe();
		}).doOnNext(p -> {
			cliente.setId(null);
			cliente.setEstado("1");
			clienteRepository.save(cliente).subscribe();
		});
	}


	@Override
	public Mono<Cliente> clienteForID(String id) {
		return clienteRepository.findById(id);
	}


	@Override
	public Flux<Cliente> findByRucOrName(String ruc, String razonSocial) {
		if(razonSocial.equalsIgnoreCase(""))
				razonSocial = ruc;
		return clienteRepository.findByRucOrName(ruc, razonSocial);
	}

}
