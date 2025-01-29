package model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "ingresos")
public class Ingreso implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private Long codIngreso;
	
	private String numeroIngreso;
	
	private String tipoMercaderia;
	
	private String pedidoDeposito;
	
	private String codigoDua;
	
	private String serie;
	
	private String estado;
	
	private String estadoRegistro;
	
	private String ruc;
	
	private String razonSocial;
	
	private String direccion;
	
	private String descripcion;
	
	//private List<Mercaderia> mercaderias;
	
	private Date fechaIngreso;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;
	

}
