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
@Document(collection = "clientes")
public class Cliente {
	
	@Id
	private String id;
	
	private String ruc;
	
	private String razonSocial;
	
	private String direccion;
	
	private String email;
	
	private String ubigeo;
	
	private String estado;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;

}
