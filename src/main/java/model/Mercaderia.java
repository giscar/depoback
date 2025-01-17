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
	
	private Long codServicio;
	
	private String numeroServicio;
	
	private String dua;
	
	private String razonSocial;
	
	private String ruc;
	
	private String productoCodigo;
	
	private String descripcion;
	
	private String unidadMedida;
	
	private Integer cantidad;
	
	private Integer saldo;
	
	private Integer nroOrden;
	
	private Date fechaIngreso;
	
	private Date fechaSalida;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;

}
