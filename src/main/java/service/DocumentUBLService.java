package service;

import org.springframework.http.ResponseEntity;

import model.Factura;

public interface DocumentUBLService {
	
	public String generarFormatoFactura(Factura factura);
	
	public ResponseEntity<String>  testFactura(String trama); 

}
