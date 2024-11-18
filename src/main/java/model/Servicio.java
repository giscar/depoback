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
	
	private String estadoRegistro;
	
	private Object operador;
	
	private Object montacarga;
	
	private Object cliente;
	
	private Object[] imagenes;
	
	private String operadorId;
	
	private String montacargaId;
	
	private Double totalHoras;
	
	private Double montoServicio;
	
	private String solicitante;
	
	private String tipoServicio;
	
	private Date fechaRegistro = new Date();
	
	private String usuarioRegistro;
	
	private String fechaConclusion;
	
	private String url;
	
	private String moneda;
	
	private String observaciones;
	
	private String tipoPago;
	
	private Date fechaFacturacion;
	
	private String usuarioFacturacion;
}
