package model;

import java.util.Date;

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

	private String nombre;
	
	private String apellidoPat;
	
	private String apellidoMat;

	private String telefono;
	
	private String estado;

	private String direccion;

	private Date fechaRegistro = new Date();


}
