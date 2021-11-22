package vvr.dto;

import javax.validation.constraints.NotEmpty;

public class UtensilioDto {

	private Long id;
	
	@NotEmpty
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
