package vvr.dto;

import java.util.Set;

public class RecetaDto {

	private Long id;
	private String nombre;
	private Integer tiempoPreparacion;
	private Integer tiempoCoccion;
	private DificultadRecetaDto dificultad;
	private Set<IngredienteDto> ingredientes;
	private Set<UtensilioDto> utensilios;
	private Set<SecuenciaRecetaDto> secuencia;

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

	public Integer getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(Integer tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public Integer getTiempoCoccion() {
		return tiempoCoccion;
	}

	public void setTiempoCoccion(Integer tiempoCoccion) {
		this.tiempoCoccion = tiempoCoccion;
	}

	public DificultadRecetaDto getDificultad() {
		return dificultad;
	}

	public void setDificultad(DificultadRecetaDto dificultad) {
		this.dificultad = dificultad;
	}

	public Set<IngredienteDto> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Set<IngredienteDto> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Set<UtensilioDto> getUtensilios() {
		return utensilios;
	}

	public void setUtensilios(Set<UtensilioDto> utensilios) {
		this.utensilios = utensilios;
	}

	public Set<SecuenciaRecetaDto> getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Set<SecuenciaRecetaDto> secuencia) {
		this.secuencia = secuencia;
	}

}
