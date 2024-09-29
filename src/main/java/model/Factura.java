package model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "facturas")
public class Factura{

	@Id
	private String id;
	
	private String nroDocumento;
	
	private Long codigoFactura;
	
	private String serie;
	
	private String ruc;
	
	private String razonSocial;
	
	private String direccion;
	
	private String rucCliente;
	
	private String razonSocialCliente;
	
	private String direccionCliente;
	
	private String fechaEmision;
	
	private String horaEmision;
	
	private String tipoDocumento;
	
	private String tipoPago;
	
	private Double monto;
	
	private int moneda;
	
	private List<Servicio> serviciosFacturados;
	

}
