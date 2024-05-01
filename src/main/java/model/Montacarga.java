package model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "montacargas")
public class Montacarga {
	
	
	private String id;

	private String nombre;
	
	private String estado;

	private String serie;

	private String tonelaje;
	
	private String tipoServicio;

	private String fechaRegistro;
	
	private String usuarioRegistro;

}
