package controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;
import model.Factura;
import model.Montacarga;
import model.Operador;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.ClienteService;
import service.FacturaService;
import service.MontacargaService;
import service.OperadorService;

@CrossOrigin("*")
@RestController
public class ErpController {
	
	@Autowired
	FacturaService facturaService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	MontacargaService montacargaService;
	
	@Autowired
	OperadorService operadorService;
	
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
	
	@GetMapping(value="factura/{id}")
	public ResponseEntity<Mono<Factura>> facturaForId(@PathVariable("id") String id) {
		return new ResponseEntity<>(facturaService.facturaForId(id),HttpStatus.OK);
	}
	
	@PostMapping(value="factura")
	public ResponseEntity<Mono<Factura>> createfactura(@RequestBody Factura factura) {
		return new ResponseEntity<>(facturaService.setFactura(factura),HttpStatus.OK);
	}
	
	@PutMapping(value="factura")
	public ResponseEntity<Mono<Factura>> editfactura(@RequestBody Factura factura) {
		return new ResponseEntity<>(facturaService.setFactura(factura),HttpStatus.OK);
	}
	
	@GetMapping(value="cliente/{ruc}")
	public ResponseEntity<Flux<Cliente>> clienteForRuc(@PathVariable("ruc") String ruc) {
		return new ResponseEntity<>(clienteService.clienteForRuc(ruc),HttpStatus.OK);
	}
	
	@GetMapping(value="cliente")
	public ResponseEntity<Mono<Cliente>> clienteForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(clienteService.clienteForID(id),HttpStatus.OK);
	}
	
	@GetMapping(value="cliente/descrip")
	public ResponseEntity<Flux<Cliente>> clienteForDescripcion(@RequestParam(name = "descripcion") String descripcion) {
		return new ResponseEntity<>(clienteService.clienteForDescripcion(descripcion),HttpStatus.OK);
	}
	
	@PostMapping(value="cliente")
	public ResponseEntity<Mono<Cliente>> createCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.setCliente(cliente),HttpStatus.OK);
	}

	@PutMapping(value="cliente")
	public ResponseEntity<Mono<Cliente>> editCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.setCliente(cliente),HttpStatus.OK);
	}
	
	@GetMapping(value="montacarga")
	public ResponseEntity<Mono<Montacarga>> montacargaForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(montacargaService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping(value="montacarga/all")
	public ResponseEntity<Flux<Montacarga>> montacargaFindAll() {
		return new ResponseEntity<>(montacargaService.all(),HttpStatus.OK);
	}
	
	@PostMapping(value="montacarga")
	public ResponseEntity<Mono<Montacarga>> montacargaSave(@RequestBody Montacarga montacarga) {
		return new ResponseEntity<>(montacargaService.save(montacarga),HttpStatus.OK);
	}

	@PutMapping(value="montacarga")
	public ResponseEntity<Mono<Montacarga>> montacargaUpdate(@RequestBody Montacarga montacarga) {
		return new ResponseEntity<>(montacargaService.save(montacarga),HttpStatus.OK);
	}
	
	@GetMapping(value="operador")
	public ResponseEntity<Mono<Operador>> operadorForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(operadorService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping(value="operador/all")
	public ResponseEntity<Flux<Operador>> operadorFindAll() {
		return new ResponseEntity<>(operadorService.all(),HttpStatus.OK);
	}
	
	@PostMapping(value="operador")
	public ResponseEntity<Mono<Operador>> operadorSave(@RequestBody Operador operador) {
		return new ResponseEntity<>(operadorService.save(operador),HttpStatus.OK);
	}

	@PutMapping(value="operador")
	public ResponseEntity<Mono<Operador>> operadorUpdate(@RequestBody Operador operador) {
		return new ResponseEntity<>(operadorService.save(operador),HttpStatus.OK);
	}
}
