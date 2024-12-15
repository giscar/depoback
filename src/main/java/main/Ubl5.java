package main;

import org.springframework.beans.factory.annotation.Autowired;

import model.Factura;
import service.DocumentUBLService;
import service.impl.BolElectronica;

public class Ubl5 {
	
	@Autowired
	private DocumentUBLService UBLService;


	public static void main(String[] args) {
		Factura factura = new Factura();
		
		//UBLService.generarFormatoFactura(factura);
        
    }

}
