package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Ingreso;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.IngresoRepository;
import service.IngresoService;

@Service
public class IngresoServiceImpl implements IngresoService{
	
	@Autowired
    private IngresoRepository ingresoRepository;

	@Override
	public Flux<Ingreso> ingresoAll() {
		return ingresoRepository.findAll();
	}

	@Override
	public Mono<Object> findMaxCodServicio() {
		return ingresoRepository.findMaxCodServicio();
	}

	@Override
	public Mono<Ingreso> save(Ingreso ingreso) {
		return ingresoRepository.save(ingreso);
	}

	@Override
	public Mono<Ingreso> edit(Ingreso ingreso) {
		return ingresoRepository.save(ingreso);
	}

	@Override
	public Mono<Ingreso> ingresoByID(String id) {
		return ingresoRepository.findById(id);
	}

	@Override
	public Flux<Ingreso> findByFiltrer(String pedidoDeposito, String codigoDua, String ruc, String tipoMercaderia, String estadoRegistro) {
		if(pedidoDeposito.equals("")) {
			pedidoDeposito = "XXXXXXXXXXXXXXXXXX";
		}
		if(codigoDua.equals("")) {
			codigoDua = "XXXXXXXXXXXXXXXXXX";
		}
		return ingresoRepository.findByFiltrer(pedidoDeposito, codigoDua, ruc, tipoMercaderia, estadoRegistro);
	}
	
}
