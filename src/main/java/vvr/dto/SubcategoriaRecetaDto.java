package vvr.dto;

import java.util.Set;

public class SubcategoriaRecetaDto {
	
	private Long id;
	private String subcategoria;
	private Set<CategoriaRecetaDto> categoria;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public Set<CategoriaRecetaDto> getCategoria() {
		return categoria;
	}
	public void setCategoria(Set<CategoriaRecetaDto> categoria) {
		this.categoria = categoria;
	}
	

}
