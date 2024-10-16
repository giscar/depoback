package controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;
import model.Factura;
import model.Imagen;
import model.Montacarga;
import model.Operador;
import model.Servicio;
import model.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.ClienteService;
import service.FacturaService;
import service.ImagenService;
import service.MontacargaService;
import service.OperadorService;
import service.ServicioService;
import service.UsuarioService;

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
	
	@Autowired
	ServicioService servicioService;
	
	@Autowired
	ImagenService imagenService;
	
	@Autowired
	UsuarioService usuarioService;
	
	
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
	
	/*@PostMapping(value="factura")
	public ResponseEntity<Mono<Factura>> createfactura(@RequestBody Factura factura) {
		return new ResponseEntity<>(facturaService.setFactura(factura),HttpStatus.OK);
	}*/
	
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
	
	@GetMapping(value="cliente/busqueda")
	public ResponseEntity<Flux<Cliente>> clienteForRucAndName(@RequestParam(name = "ruc") String ruc, @RequestParam(name = "razonSocial") String razonSocial) {
		return new ResponseEntity<>(clienteService.findByRucOrName(ruc, razonSocial),HttpStatus.OK);
	}
	
	@PostMapping(value="cliente")
	public ResponseEntity<Mono<Cliente>> createCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.save(cliente),HttpStatus.OK);
	}

	@PutMapping(value="cliente")
	public ResponseEntity<Mono<Cliente>> editCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.edit(cliente),HttpStatus.OK);
	}
	
	@GetMapping(value="montacarga")
	public ResponseEntity<Mono<Montacarga>> montacargaForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(montacargaService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping(value="montacarga/estado")
	public ResponseEntity<Flux<Montacarga>> montacargaForActivo() {
		return new ResponseEntity<>(montacargaService.findByEstado(),HttpStatus.OK);
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
		return new ResponseEntity<>(montacargaService.edit(montacarga),HttpStatus.OK);
	}
	
	@PutMapping(value="montacarga/inactiva")
	public ResponseEntity<Mono<Montacarga>> montacargaInactiva(@RequestBody Montacarga montacarga) {
		return new ResponseEntity<>(montacargaService.inactiva(montacarga),HttpStatus.OK);
	}
	
	@GetMapping(value="operador")
	public ResponseEntity<Mono<Operador>> operadorForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(operadorService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping(value="operador/documento")
	public ResponseEntity<Mono<Operador>> operadorByDocumento(@RequestParam(name = "documento") String documento) {
		return new ResponseEntity<>(operadorService.findByEstadoByDocumento(documento),HttpStatus.OK);
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
	
	@GetMapping(value="operador/estado")
	public ResponseEntity<Flux<Operador>> operadorForActivo() {
		return new ResponseEntity<>(operadorService.findByEstado(),HttpStatus.OK);
	}
	
	@GetMapping(value="usuario")
	public ResponseEntity<Mono<Usuario>> usuarioForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(usuarioService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping(value="usuario/documento")
	public ResponseEntity<Mono<Usuario>> usuarioByDocumento(@RequestParam(name = "documento") String documento) {
		return new ResponseEntity<>(usuarioService.findByEstadoByDocumento(documento),HttpStatus.OK);
	}
	
	@GetMapping(value="usuario/all")
	public ResponseEntity<Flux<Usuario>> usuarioFindAll() {
		return new ResponseEntity<>(usuarioService.all(),HttpStatus.OK);
	}
	
	@PostMapping(value="usuario")
	public ResponseEntity<Mono<Usuario>> usuarioSave(@RequestBody Usuario usuario) {
		return new ResponseEntity<>(usuarioService.save(usuario),HttpStatus.OK);
	}

	@PutMapping(value="usuario")
	public ResponseEntity<Mono<Usuario>> usuarioUpdate(@RequestBody Usuario usuario) {
		return new ResponseEntity<>(usuarioService.save(usuario),HttpStatus.OK);
	}
	
	@GetMapping(value="usuario/estado")
	public ResponseEntity<Flux<Usuario>> usuarioForActivo() {
		return new ResponseEntity<>(usuarioService.findByEstado(),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/all")
	public ResponseEntity<Flux<Servicio>> servicioFindAll() {
		return new ResponseEntity<>(servicioService.all(),HttpStatus.OK);
	}
	
	@PostMapping(value="servicio")
	public ResponseEntity<Mono<Servicio>> servicioSave(@RequestBody Servicio servicio){
		return new ResponseEntity<>(servicioService.save(servicio),HttpStatus.OK);
	}

	@PostMapping("servicio/upload")
	public Mono<String> cargarImagen(@RequestPart("file") Flux<FilePart> filePart, @RequestPart("id") String id,
			@RequestPart("type") String type, @RequestPart("size") String size) {
		return imagenService.cargarFile(filePart, id, type, size);
	}
	
	@GetMapping(value="servicio/uploadInactive")
	public Mono<Imagen> inactiveImagen(@RequestParam(name = "id") String id) {
		return imagenService.findById(id).flatMap(p -> {
			p.setEstado("0");
			return imagenService.save(p);
		});
	}
	
	@GetMapping(value="servicio")
	public ResponseEntity<Mono<Servicio>> servicioForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(servicioService.findByIdAggregate(id),HttpStatus.OK);
	}

	@PutMapping(value="servicio")
	public ResponseEntity<Mono<Servicio>> servicioUpdate(@RequestBody Servicio servicio) {
		return new ResponseEntity<>(servicioService.save(servicio),HttpStatus.OK);
	}
	
	@DeleteMapping(value="servicio/imagen/eliminar")
	public void deleteFile(@RequestParam(name = "id") String id) {
		imagenService.delete(id); 
	}
	
	@GetMapping(value="servicio/estado")
	public ResponseEntity<Flux<Servicio>> servicioForActivo(@RequestParam(name = "estado") String estado) {
		return new ResponseEntity<>(servicioService.findByEstado(estado),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busqueda")
	public ResponseEntity<Flux<Servicio>> servicioForRucAndCodServicio(@RequestParam(name = "codServicio") Integer codServicio, @RequestParam(name = "ruc") String ruc) {
		return new ResponseEntity<>(servicioService.findByRucCodServicio(ruc, codServicio),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaAggregate")
	public ResponseEntity<Flux<Servicio>> findByRucCodServicioAggregate(@RequestParam(name = "codServicio", required = false) Integer codServicio, @RequestParam(name = "ruc") String ruc) {
		return new ResponseEntity<>(servicioService.findByRucCodServicioAggregate(ruc, codServicio),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaAggregateEstadoConcluido")
	public ResponseEntity<Flux<Servicio>> findByRucCodServicioAggregateEstadoRegistro(@RequestParam(name = "codServicio", required = false) Integer codServicio, @RequestParam(name = "ruc", required = false) String ruc) {
		return new ResponseEntity<>(servicioService.findByRucCodServicioAggregateConcluido(ruc, codServicio),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaEstadistica")
	public ResponseEntity<Flux<Servicio>> findByEstdisticaAggregate(
			@RequestParam(name = "codServicio", required = false) Integer codServicio, 
			@RequestParam(name = "ruc") String ruc,
			@RequestParam(name = "idOperador") String idOperador,
			@RequestParam(name = "idMontacarga") String idMontacarga,
			@RequestParam(name = "estadoRegistro") String estadoRegistro,
			@RequestParam(name = "tipoServicio") String tipoServicio) {
		return new ResponseEntity<>(servicioService.findByRucEstadisticasAggregate(ruc, codServicio, idOperador, idMontacarga, estadoRegistro, tipoServicio),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaIdOperadorAggregate")
	public ResponseEntity<Flux<Servicio>> findByOperadorIdAggregate(@RequestParam(name = "idOperador", required = false) String idOperador) {
		return new ResponseEntity<>(servicioService.findByOperadorIdAggregate(idOperador),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaServiciosPendientes")
	public ResponseEntity<Flux<Servicio>> findByOperadorIdAggregate() {
		return new ResponseEntity<>(servicioService.findByServiciosPendientes(),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaServiciosParaFacturar")
	public ResponseEntity<Flux<Servicio>> findByServiciosFactura(@RequestParam(name = "idServicios", required = false) String idServicios) {
		String[] arrayIdServicios = idServicios.split(",");
		return new ResponseEntity<>(servicioService.findByServiciosConcluidosInServicios(arrayIdServicios),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaServiciosConcluidos")
	public ResponseEntity<Flux<Servicio>> findByServiciosConcluidos() {
		return new ResponseEntity<>(servicioService.findByServiciosConcluidos(),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/maxCodServicio")
	public ResponseEntity<Mono<Object>> findMaxCodServicioAggregate() {
		return new ResponseEntity<>(servicioService.findMaxCodServicio(),HttpStatus.OK);
	}
	
	@PostMapping("factura")
	public ResponseEntity<Mono<Factura>> generarFactura(@RequestBody Factura factura) {
		return new ResponseEntity<>(facturaService.save(factura),HttpStatus.OK);
	}
	
	@GetMapping(value="factura/maxCodFactura")
	public ResponseEntity<Mono<Object>> findMaxCodFactura() {
		return new ResponseEntity<>(facturaService.findCodigoFactura(),HttpStatus.OK);
	}
}
