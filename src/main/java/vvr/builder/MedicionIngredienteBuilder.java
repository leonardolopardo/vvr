package vvr.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vvr.dto.MedicionIngredienteDto;
import vvr.model.MedicionIngrediente;

/**
 * MedicionIngredienteBuilder
 * @author Leo
 *
 */
@Component
public class MedicionIngredienteBuilder implements IBuilder<MedicionIngrediente, MedicionIngredienteDto> {

	/**
	 * Transforma una MedicionIngredienteDto a una MedicionIngrediente 
	 * @param dto
	 * @return
	 */
	@Override
	public MedicionIngrediente builderToModel(MedicionIngredienteDto dto) {
		MedicionIngrediente medicionIngrediente = new MedicionIngrediente();
		medicionIngrediente.setId(dto.getId());
		medicionIngrediente.setNomenclatura(dto.getNomenclatura());
		medicionIngrediente.setUnidadMedida(dto.getUnidadMedida());
		return medicionIngrediente;
	}

	@Override
	public MedicionIngredienteDto builderToDto(MedicionIngrediente model) {
		MedicionIngredienteDto miDto = new MedicionIngredienteDto();
		miDto.setId(model.getId());
		miDto.setNomenclatura(model.getNomenclatura());
		miDto.setUnidadMedida(model.getUnidadMedida());
		return miDto;
	}

	@Override
	public List<MedicionIngredienteDto> builderListToDto(List<MedicionIngrediente> list) {
		List<MedicionIngredienteDto> listDto = new ArrayList<>();
		
		for (MedicionIngrediente mi : list) {
			MedicionIngredienteDto dto = new MedicionIngredienteDto();
			dto.setId(mi.getId());
			dto.setUnidadMedida(mi.getUnidadMedida());
			dto.setNomenclatura(mi.getNomenclatura());
			
			listDto.add(dto);
		}
		
		return listDto;
	}


}
