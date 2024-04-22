package model;

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
	
	private String ruc;
	
	private Double monto;
	
	private int moneda;
	

}
