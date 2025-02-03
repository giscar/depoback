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
@Document(collection = "salidas")
public class Salida {
	
	@Id
	private String id;
	
	private String idMercaderia;
	
	private String numeroMercaderia;
	
	private Long cantidadSalida;
	
	private Long saldoRestante;
	
	private String descripcionSalida;
	
	private Date fechaSalida;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;

}
