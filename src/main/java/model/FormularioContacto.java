package model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "formularioContactos")
public class FormularioContacto {
	
	@Id
	private String id;
	
	private String nombres;
	
	private String apellidos;
	
	private String email;
	
	private String mensaje;
	
	private Date fechaRegistro = new Date();

}
