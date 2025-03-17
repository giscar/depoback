package model;

import java.util.Date;
import java.util.List;

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
	
	private Cliente agencia;
	
	private Cliente empresa;
	
	private String placaVehiculo;

	private String chofer;

	private Date fechaRecepcion = new Date();
	
	private String observaciones;
	
	private String almacenado;
	
	private List<Mercaderia> mercaderias;

}
