package vvr.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vvr.dto.SecuenciaRecetaDto;
import vvr.model.SecuenciaReceta;

/**
 * SecuenciaBuilder
 * @author Leo
 *
 */
@Component
public class SecuenciaBuilder implements IBuilder<SecuenciaReceta, SecuenciaRecetaDto>{
	
	/**
	 * Crea una nueva instancia de SecuenciaRecetaDto dado SecuenciaRecetaModel
	 * @param model
	 * @return
	 */
	private SecuenciaRecetaDto newBuilderToDto(SecuenciaReceta model) {
		SecuenciaRecetaDto dto = new SecuenciaRecetaDto();
		dto.setId(model.getId());
		dto.setDescripcion(model.getDescripcion());
		dto.setNumero(model.getNumero());
		return dto;
	}
	
	/**
	 * Crea una nueva instancia de SecuenciaRecetaModel dado un SecuenciaRecetaDto
	 * @param dto
	 * @return
	 */
	@Override
	public SecuenciaReceta builderToModel (SecuenciaRecetaDto dto) {
		SecuenciaReceta secuencia = new SecuenciaReceta();
		secuencia.setId(dto.getId());
		secuencia.setNumero(dto.getNumero());
		secuencia.setDescripcion(dto.getDescripcion());
		return secuencia;
	}

	/**
	 * Crea una SecuenciaRecetaDto dado un SecuenciaRecetaModel
	 */
	@Override
	public SecuenciaRecetaDto builderToDto(SecuenciaReceta model) {
		return newBuilderToDto(model);
	}

	/**
	 * Devuelve un List<SecuenciaRecetaDto> dado un List<SecuenciaReceta>Model
	 */
	@Override
	public List<SecuenciaRecetaDto> builderListToDto(List<SecuenciaReceta> list) {
		List<SecuenciaRecetaDto> listDto = new ArrayList<>();
		
		for (SecuenciaReceta model : list) {
			listDto.add(newBuilderToDto(model));
		}
		return listDto;
	}

}
