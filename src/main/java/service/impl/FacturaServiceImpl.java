package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Factura;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.FacturaRepository;
import service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService{
	
	@Autowired
	private FacturaRepository facturaRepository;
	

	@Override
	public Flux<Factura> listFactura() {
		return facturaRepository.findAll();
	}

	/*@Override
	public Mono<Void> createFactura(Factura factura) {
		return facturaForId(factura.getId())
				.switchIfEmpty(Mono.just(factura)
						.flatMap(p -> facturaRepository.save(p)	
						)).then();
	}*/
	
	@Override
	public Mono<Factura> createFactura(Factura factura) {
		return facturaRepository.save(factura);
						
	}

	@Override
	public Mono<Factura> updateFactura(Factura factura) {
		return facturaForId(factura.getId())
				.map(p -> {
					p.setMonto(factura.getMonto());
					p.setMoneda(factura.getMoneda());
					p.setRuc(factura.getRuc());
					p.setId(factura.getId());
					return p;
				});
	}

	@Override
	public Flux<Factura> searchFactura(String rucCliente) {
		return listFactura().filter(p -> p.getRuc().equals(rucCliente));
	}

	@Override
	public Mono<Factura> facturaForId(String id) {
		return facturaRepository.findById(id);
	}

}
