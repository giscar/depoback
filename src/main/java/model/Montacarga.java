package model;

import java.util.Date;

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

	private String codigo;
	
	private String marca;

	private String serie;

	private String tonelaje;
	
	private String modelo;
	
	private String color;
	
	private String anhoFabricacion;
	
	private String ubicacion;
	
	private String estado;
	
	private String revisionOperatividad;
	
	private String estadoRegistro;
	
	private Date fechaRegistro = new Date();

}