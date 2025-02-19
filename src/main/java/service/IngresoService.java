package service;

import model.Ingreso;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngresoService {
	
	Flux<Ingreso> ingresoAll();
	
	Mono<Ingreso> save(Ingreso ingreso);
	
	Mono<Ingreso> edit(Ingreso ingreso);
	
	Mono<Ingreso> ingresoByID(String id);
	
	Mono<Object> findMaxCodServicio();
	
	Flux<Ingreso> findByFiltrer(String pedidoDeposito, 
			String codigoDua, String ruc, String tipoMercaderia, String estadoRegistro);

}
