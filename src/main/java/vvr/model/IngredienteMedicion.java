package vvr.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingrediente_medicion_ingrediente")
public class IngredienteMedicion {
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ingrediente", referencedColumnName = "id")
	private Ingrediente ingrediente;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_medicion_ingrediente", referencedColumnName = "id")
	private MedicionIngrediente mi;

	@Column(name = "cantidad")
	private Double cantidad;

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public MedicionIngrediente getMi() {
		return mi;
	}

	public void setMi(MedicionIngrediente mi) {
		this.mi = mi;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

}
