package service.impl;

import java.nio.file.Paths;

import org.apache.commons.lang3.RandomStringUtils;
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
	
	private static String ruta = "/Users/carlosleon/requerimientos/2024/depovent/depofront/public/images/";
	

    public Mono<Imagen> save(Imagen imagen) { 
        return imagenRepository.save(imagen); 
    }
    
    public Mono<String> cargarFile(Flux<FilePart> filePart, String id, String type, String size) {
    	String namefile = RandomStringUtils.randomAlphanumeric(20);
    	return filePart.flatMap(p -> p.transferTo(Paths.get(ruta,namefile+getFileExtension(p.filename())))
    		      .then(Mono.just(p.filename())))
    		      .collectList()
    		      .flatMap(filenames -> {
    		    	  Imagen img = new Imagen();
    		    	  img.setIdServicio(id);
    		    	  img.setFilename(namefile+getFileExtension(filenames.get(0)));
    		    	  img.setEstado("1");
    		    	  img.setType(type);
    		    	  img.setSize(size);
    		          return imagenRepository.save(img);
    		      }).then(Mono.just("ok"))
    		      .onErrorResume(error -> Mono.error(error));
    }
    
    private String getFileExtension(String filename) {
        int lastIndexOf = filename.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return filename.substring(lastIndexOf);
    }
    

}
