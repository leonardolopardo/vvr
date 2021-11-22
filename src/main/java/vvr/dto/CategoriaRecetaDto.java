package vvr.dto;

import java.util.Set;

public class CategoriaRecetaDto {

	private Long id;
	private String categoria;
	private Set<SubcategoriaRecetaDto> subcategorias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Set<SubcategoriaRecetaDto> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(Set<SubcategoriaRecetaDto> subcategorias) {
		this.subcategorias = subcategorias;
	}

}
