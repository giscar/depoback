package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.Imagen;

public interface ImagenRepository extends ReactiveMongoRepository<Imagen, String> {
	

}
