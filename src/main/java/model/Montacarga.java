package model;

import java.time.Instant;

import domain.type.EstadoMontacarga;
import domain.type.EstadoRegister;
import domain.type.EstadoRevision;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "montacargas")
public class Montacarga {

	@Id
	private String id;

	@Indexed(unique = true)
	private String codigo;

	private String marca;

	private String serie;

	private String tonelaje;

	private String modelo;

	private String color;

	private Integer anhoFabricacion;

	private String ubicacion;

	private EstadoMontacarga estado;

	private EstadoRevision revisionOperatividad;

	@Setter
	private EstadoRegister estadoRegistro;

	@CreatedDate
	private Instant fechaRegistro;

	private String usuarioRegistro;

	private Boolean inactivo;
}
