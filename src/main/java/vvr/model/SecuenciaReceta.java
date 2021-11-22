package vvr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represeta un paso a ejecutar para realizacion de la Receta Ej- Paso 1: Hervir
 * el agua, Paso 2: Colocar sal, Paso 3: Colocar las pastas dentro, ...
 * 
 * @author Leo
 *
 */
@Entity
@Table(name = "paso_receta")
public class SecuenciaReceta {

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "descripcion")
	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}