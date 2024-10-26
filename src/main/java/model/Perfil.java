package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "perfiles")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String codigo;
	
	private String descripcion;
	
	private String estado;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;
	
	private String indInactivo;
	
	private List<Rol> roles;

}
