package service;

import model.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {
	
	Flux<Cliente> clienteForRuc(String ruc);
	
	Flux<Cliente> clienteForDescripcion(String descripcion);
	
	Flux<Cliente> findByRucOrName(String ruc, String razonSocial);
	
	Mono<Cliente> setCliente(Cliente cliente);
	
	Mono<Cliente> clienteForID(String id);

}
