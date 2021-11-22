package vvr.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Representa la receta creada por el Usuario
 * 
 * @author Leo
 *
 */
@Entity
@Table(name = "receta")
public class Receta {

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "tiempo_preparacion")
	private Integer tiempoPreparacion;

	@Column(name = "tiempo_coccion")
	private Integer tiempoCoccion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_dificultad_receta", referencedColumnName = "id")
	private Dificultad dificultad;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "receta_utensilio", catalog = "rv", joinColumns = {
			@JoinColumn(name = "id_receta", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_utensilio", nullable = false, updatable = false) })
	private Set<Utensilio> utensilios;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "receta_paso", catalog = "rv", joinColumns = {
			@JoinColumn(name = "id_receta", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_paso", nullable = false, updatable = false) })
	private Set<SecuenciaReceta> secuencia;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "receta_subcategoria", catalog = "rv", joinColumns = {
			@JoinColumn(name = "id_receta", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_subcategoria", nullable = false, updatable = false) })
	private Set<Subcategoria> subcategoria;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "receta_ingrediente", catalog = "rv", joinColumns = {
			@JoinColumn(name = "id_receta", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_ingrediente_medicion", nullable = false, updatable = false) })
	private Set<IngredienteMedicion> ingredienteMedicion;

//	private Usuario usuario;

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

	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}

	public Set<Utensilio> getUtensilios() {
		return utensilios;
	}

	public void setUtensilios(Set<Utensilio> utensilios) {
		this.utensilios = utensilios;
	}

	public Set<SecuenciaReceta> getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Set<SecuenciaReceta> secuencia) {
		this.secuencia = secuencia;
	}

	public Set<Subcategoria> getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Set<Subcategoria> subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Set<IngredienteMedicion> getIngredienteMedicion() {
		return ingredienteMedicion;
	}

	public void setIngredienteMedicion(Set<IngredienteMedicion> ingredienteMedicion) {
		this.ingredienteMedicion = ingredienteMedicion;
	}

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

}