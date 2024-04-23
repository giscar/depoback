package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "servicio")
public class Servicio {
	
	@Id
	private String id;
	
	private String codServicio;
	
	private String ruc;
	
	private String horaSalidaLocal;
	
	private String horaInicioServicio;
	
	private String horaFinServicio;
	
	private String horaRetornoLocal;
	

}
