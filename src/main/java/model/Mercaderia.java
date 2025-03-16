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
	
	private Long codIngreso;
	
	private String numeroMercaderia;
	
	private Long codMercaderia;
	
	private String serie;
	
	private String productoCodigo;
	
	private String descripcionProducto;
	
	private Catalogo unidadMedida;
	
	private Catalogo almacen;
	
	private Integer cantidad;
	
	private Integer cantidadOrignal;
	
	private Integer saldo;
	
	private Integer nroOrden;
	
	private String estadoMercaderia;
	
	private Date fechaIngreso;
	
	private Date fechaSalida;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;

}
