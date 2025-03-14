package model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "ordenSalida")
public class OrdenSalida {
	
	private String id;
	
	private String codigoSalida;
	
	private Long numeroSalida;
	
	private String idMercaderiaSalida;
	
	private String rucDestinatario;
	
	private String razonSocialDestinatario;
	
	private String direccionDestinatario;
	
	private Date fechaEmision;
	
	private Date fechaInicioTranslado;
	
	private String marcaVehiculo;
	
	private String placaVehiculo;
	
	private String chofer;
	
	private String licencia;
	

}
