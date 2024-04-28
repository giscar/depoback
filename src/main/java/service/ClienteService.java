package service;

import org.bson.types.ObjectId;

import model.Cliente;
import model.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {
	
	Flux<Cliente> clienteForRuc(String ruc);
	
	Flux<Cliente> clienteForDescripcion(String descripcion);

}
