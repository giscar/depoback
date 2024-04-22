package controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import model.Factura;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.FacturaService;

@CrossOrigin("*")
@RestController
public class ErpController {
	
	@Autowired
	FacturaService facturaService;
	
	@GetMapping(value = "names")
	public Flux<String> getNames(){
		List<String> lista = List.of("uno", "dos", "tres", "cuatro");
		return Flux.fromIterable(lista)
				.delayElements(Duration.ofSeconds(2));
				
	}
	
	@GetMapping(value = "factura/all")
	public ResponseEntity<Flux<Factura>> getFacturas(){
		return new ResponseEntity<>(facturaService.listFactura(), HttpStatus.OK) ;
	}
	
	@GetMapping(value="factura")
	public ResponseEntity<Mono<Factura>> productoCodigo(@RequestParam("id") String id) {
		return new ResponseEntity<>(facturaService.facturaForId(id),HttpStatus.OK);
	}
	
	@PostMapping(value="factura")
	public ResponseEntity<Mono<Factura>> nuevaFactura(@RequestBody Factura factura) {
		return new ResponseEntity<>(facturaService.createFactura(factura),HttpStatus.OK);
	}
	
	

}
