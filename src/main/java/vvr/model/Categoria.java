package vvr.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa la categoria de la receta
 * Ej. Plato principal, bebidas, postres, ...
 * @author Leo
 *
 */
@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue
    @Column(name = "id", unique = true , nullable = false )
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = false)
	private Set<Subcategoria> subcategorias;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String categoria) {
		this.nombre = categoria;
	}

	public Set<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(Set<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
}