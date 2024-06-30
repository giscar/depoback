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
@Document(collection = "servicios")
public class Servicio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private Long codServicio;
	
	private String ruc;
	
	private String horaSalidaLocal;
	
	private String horaInicioServicio;
	
	private String horaFinServicio;
	
	private String horaRetornoLocal;
	
	private String estado;
	
	private Object operador;
	
	private Object montacarga;
	
	private Object cliente;
	
	private Object[] imagenes;
	
	private String operadorId;
	
	private String montacargaId;
	
	private Double totalHoras;
	
	private Double montoServicio;
	
	private Date fechaRegistro = new Date();
}
