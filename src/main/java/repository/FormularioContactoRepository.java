package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import model.FormularioContacto;

public interface FormularioContactoRepository extends ReactiveMongoRepository<FormularioContacto, String> {
	
	

}
