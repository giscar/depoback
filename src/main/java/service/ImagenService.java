package service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import model.Imagen;
import reactor.core.publisher.Mono;

public interface ImagenService {
	
	public Mono<Imagen> addImagen(String title, MultipartFile file) throws IOException;
	
	public Mono<Imagen> getImagen(String id);

}
