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
@Document(collection = "roles")
public class Rol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String codigo;
	
	private String descripcion;
	
	private String estado;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;
	
	private String indInactivo;

}
