package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.Cliente;
import model.Factura;
import model.Imagen;
import model.Montacarga;
import model.Operador;
import model.Servicio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.ClienteService;
import service.FacturaService;
import service.MontacargaService;
import service.OperadorService;
import service.ServicioService;
import service.impl.ImagenServiceImpl;

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
	ImagenServiceImpl imagenService;
	
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
	
	@GetMapping(value="cliente/busqueda")
	public ResponseEntity<Flux<Cliente>> clienteForRucAndName(@RequestParam(name = "ruc") String ruc, @RequestParam(name = "razonSocial") String razonSocial) {
		return new ResponseEntity<>(clienteService.findByRucOrName(ruc, razonSocial),HttpStatus.OK);
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
	
	@GetMapping(value="montacarga/estado")
	public ResponseEntity<Flux<Montacarga>> montacargaForActivo(@RequestParam(name = "estado") String estado) {
		return new ResponseEntity<>(montacargaService.findByEstado(estado),HttpStatus.OK);
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
	
	@GetMapping(value="operador/estado")
	public ResponseEntity<Flux<Operador>> operadorForActivo(@RequestParam(name = "estado") String estado) {
		return new ResponseEntity<>(operadorService.findByEstado(estado),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio")
	public ResponseEntity<Mono<Servicio>> servicioForId(@RequestParam(name = "id") String id) {
		return new ResponseEntity<>(servicioService.findByIdAggregate(id),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/all")
	public ResponseEntity<Flux<Servicio>> servicioFindAll() {
		return new ResponseEntity<>(servicioService.all(),HttpStatus.OK);
	}
	
	@PostMapping(value="servicio")
	public ResponseEntity<Mono<Servicio>> servicioSave(@RequestBody Servicio servicio){
		return new ResponseEntity<>(servicioService.save(servicio),HttpStatus.OK);
	}
	
	@PostMapping("servicio/uploadFile")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile image) {
		System.out.println(image);
		return null;
		/*String uploadImage = service.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);*/
	}
	
	@PostMapping("/imagen/add")
	public Mono<Imagen> addImagen(@RequestParam("title") String title, 
	  @RequestParam("image") MultipartFile image, Model model) 
	  throws IOException {
	    return imagenService.addImagen(title, image);
	}
	
	/*@GetMapping("/imagen/{id}")
	public String getImagen(@PathVariable String id, Model model) {
		Imagen imagen = imagenService.getImagen(id);
	    model.addAttribute("title", imagen.getTitle());
	    model.addAttribute("image", 
	      Base64.getEncoder().encodeToString(imagen.getImage().getData()));
	    return "photos";
	}*/
	
	@PostMapping("/upload-files")
	public Mono uploadFileWithoutEntity(@RequestPart("files") Flux<FilePart> filePartFlux) {
	    return filePartFlux.flatMap(file -> file.transferTo(Paths.get(file.filename())))
	      .then(Mono.just("OK"))
	      .onErrorResume(error -> Mono.just("Error uploading files"+error));
	}
	///Users/carlosleon/requerimientos/2024/depovent/depofront/public/images
	@PostMapping("servicio/upload-files2")
	public Mono uploadFileWithoutEntity2(@RequestPart("files") Flux<FilePart> filePartFlux) {
		
		return filePartFlux.flatMap(file -> file.transferTo(Paths.get("/Users/carlosleon/requerimientos/2024/depovent/depofront/public/images/",file.filename())))
			      .then(Mono.just("OK"))
			      .onErrorResume(error -> Mono.just("Error uploading files"+error));
	}

	@PutMapping(value="servicio")
	public ResponseEntity<Mono<Servicio>> servicioUpdate(@RequestBody Servicio servicio) {
		return new ResponseEntity<>(servicioService.save(servicio),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/estado")
	public ResponseEntity<Flux<Servicio>> servicioForActivo(@RequestParam(name = "estado") String estado) {
		return new ResponseEntity<>(servicioService.findByEstado(estado),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busqueda")
	public ResponseEntity<Flux<Servicio>> servicioForRucAndCodServicio(@RequestParam(name = "codServicio") String codServicio, @RequestParam(name = "ruc") String ruc) {
		return new ResponseEntity<>(servicioService.findByRucCodServicio(ruc, codServicio),HttpStatus.OK);
	}
	
	@GetMapping(value="servicio/busquedaAggregate")
	public ResponseEntity<Flux<Servicio>> findByRucCodServicioAggregate(@RequestParam(name = "codServicio") String codServicio, @RequestParam(name = "ruc") String ruc) {
		return new ResponseEntity<>(servicioService.findByRucCodServicioAggregate(ruc, codServicio),HttpStatus.OK);
	}
	
	
}
