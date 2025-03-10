package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.OrdenSalida;

public interface OrdenSalidaRepository extends ReactiveMongoRepository<OrdenSalida, String> {
	
	
}
