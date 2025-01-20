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
@Document(collection = "mercaderias")
public class Mercaderia {
	
	@Id
	private String id;
	
	private String idIngreso;
	
	private String productoCodigo;
	
	private String descripcionProducto;
	
	private String unidadMedida;
	
	private Integer cantidad;
	
	private Integer saldo;
	
	private Integer nroOrden;
	
	private Date fechaIngreso;
	
	private Date fechaSalida;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;

}
