package model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "operadores")
public class Operador {
	
	private String id;
	
	private String documento;

	private String email;

	private String nombre;

	private String movil;
	
	private String estado;

	private String direccion;

	


}
