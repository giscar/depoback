package service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Factura;
import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.FacturaRepository;
import service.DocumentUBLService;
import service.FacturaService;
import service.ServicioService;

@Service
public class FacturaServiceImpl implements FacturaService{
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private DocumentUBLService UBLService;
	
	@Autowired
	private ServicioService servicioService;
	

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
	public Mono<Factura> setFactura(Factura factura) {
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

	@Override
	public Mono<Factura> save(Factura factura) {
		//return Mono.just(UBLService.clienteForRuc(factura));
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		factura.setHoraEmision(dateFormat.format(date));
		for (Servicio serv : factura.getServicios()) {
				//servicioService.facturarServicio(serv);
		}
		UBLService.generarFormatoFactura(factura);
		return facturaRepository.save(null).map(p -> {
			
			BolElectronica.generarXMLZipiadoBoleta();
			return p;
		});
	}

	@Override
	public Mono<Object> findCodigoFactura() {
		return facturaRepository.findMaxCodFactura();
	}

}
