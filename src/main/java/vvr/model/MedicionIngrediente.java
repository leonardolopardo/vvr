package vvr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa la forma de medir un ingrediente
 * 
 * @author Leo
 *
 */
@Entity
@Table(name = "medicion_ingrediente")
public class MedicionIngrediente {

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * ejemplo cm3, gramos.
	 */
	@Column(name = "unidad_medida")
	private String unidadMedida;

	/**
	 * ejemplo cucharada sopera, cucharada de te.
	 */
	@Column(name = "nomenclatura")
	private String nomenclatura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getNomenclatura() {
		return nomenclatura;
	}

	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

}
