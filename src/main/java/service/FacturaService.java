package service;

import org.bson.types.ObjectId;

import model.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FacturaService {
	
	Flux<Factura> listFactura();
	
	Flux<Factura> searchFactura(String rucCliente);
	
	Mono<Factura> createFactura(Factura factura);
	
	Mono<Factura> updateFactura(Factura factura);
	
	Mono<Factura> facturaForId(String id);

}
