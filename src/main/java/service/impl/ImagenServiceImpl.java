package service.impl;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import model.Imagen;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.ImagenRepository;
import service.ImagenService;

@Service
public class ImagenServiceImpl implements ImagenService{
	
	@Autowired
    private ImagenRepository imagenRepository;
	

    public Mono<Imagen> save(Imagen imagen) { 
        return imagenRepository.save(imagen); 
    }

    /*public Mono<String> cargarFile(Flux<FilePart> filePartFlux) { 
    	return filePartFlux.flatMap(
    			file -> 
    		file.transferTo(Paths.get("/Users/carlosleon/requerimientos/2024/depovent/depofront/public/images/",file.filename())))
			      .then(Mono.just("OK"))
			      .onErrorResume(error -> Mono.just("Error uploading files"+error));
			      
    }*/
    
    private static String ruta = "/Users/carlosleon/requerimientos/2024/depovent/depofront/public/images/";
    
    public Mono<String> cargarFile(Flux<FilePart> filePart) { 
    	Imagen img = new Imagen();
    	return filePart.flatMap(p -> p.transferTo(Paths.get(ruta,p.filename()))
    		      .then(Mono.just(p.filename())))
    		      .collectList()
    		      .flatMap(filenames -> {
    		    	  img.setFilename(filenames.get(0));
    		          return imagenRepository.save(img);
    	
    		      }).then(Mono.just("dd"))
    		      .onErrorResume(error -> Mono.error(error));
		      
    }
    

}
