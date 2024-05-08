package service;

import model.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FacturaService {
	
	Flux<Factura> listFactura();
	
	Flux<Factura> searchFactura(String rucCliente);
	
	Mono<Factura> setFactura(Factura factura);
	
	Mono<Factura> updateFactura(Factura factura);
	
	Mono<Factura> facturaForId(String id);

}
