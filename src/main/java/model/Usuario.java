package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String nombre;
	
	private String apellidoPat;
	
	private String apellidoMat;
	
	private String documento;
	
	private String passwd;
	
	private List<Perfil> perfiles;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;
	
	private String estado;
	
	private String indInactivo;

}
