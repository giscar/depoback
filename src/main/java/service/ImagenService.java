package service;

import org.springframework.http.codec.multipart.FilePart;

import model.Imagen;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImagenService {
	
	public Mono<Imagen> save(Imagen imagen);
	
	public Mono<String> cargarFile(Flux<FilePart> filePart, String id, String type, String size);

}
