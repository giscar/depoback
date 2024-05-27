package model;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "servicios")
public class Servicio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String codServicio;
	
	private String ruc;
	
	private String horaSalidaLocal;
	
	private String horaInicioServicio;
	
	private String horaFinServicio;
	
	private String horaRetornoLocal;
	
	private String estado;
	
	private Operador operador;
	
	private Object montacarga;
	
	private Object cliente;
	
	private String operadorId;
	
	private String montacargaId;
}
