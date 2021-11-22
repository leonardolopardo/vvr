package vvr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicionIngredienteDto {

	private Long id;
	@JsonProperty("unidad_medida")
	private String unidadMedida;
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
