package model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "notaRecepcion")
public class NotaRecepcion {
	
	private String id;
	
	private String idIngreso;
	
	private String codigoRecepcion;
	
	private Long numeroRecepcion;
	
	private String rucAgenciaAduanera;
	
	private String razonSociaAgenciaAduanera;
	
	private String rucCliente;
	
	private String razonSocialCliente;
	
	private String vehiculo;
	
	private String chofer;
	
	private Date fechaRecepcion;
	
	private String observaciones;
	
	private String almacenado;

}
