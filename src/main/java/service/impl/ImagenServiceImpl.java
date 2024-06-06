package service.impl;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.Imagen;
import reactor.core.publisher.Mono;
import repository.ImagenRepository;
import service.ImagenService;

@Service
public class ImagenServiceImpl implements ImagenService{
	
	@Autowired
    private ImagenRepository imagenRepository;
	
	/*public Mono<Imagen> addImagen(String title, MultipartFile file) throws IOException { 
        return Mono.just(new Imagen()).map(p -> {
        	p.setTitle(title);
            p.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
            imagenRepository.save(p); 
            return p;
        });
        
    }*/
	
	/*public Mono<String> addImagen(String title, MultipartFile file) throws IOException { 
        Imagen imagen = new Imagen(); 
        imagen.setTitle(title);
        imagen.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        Mono<Imagen> image = imagenRepository.save(imagen); 
        return imagen.getId(); 
    }*/

    public Mono<Imagen> getImagen(String id) { 
        return imagenRepository.findById(id); 
    }

	@Override
	public Mono<Imagen> addImagen(String title, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
