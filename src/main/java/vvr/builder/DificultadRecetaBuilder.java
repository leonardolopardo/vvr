package vvr.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vvr.dto.DificultadRecetaDto;
import vvr.model.Dificultad;

@Component
public class DificultadRecetaBuilder implements IBuilder<Dificultad, DificultadRecetaDto> {

	@Override
	public Dificultad builderToModel(DificultadRecetaDto dto) {
		Dificultad dr = new Dificultad();
		dr.setId(dto.getId());
		dr.setNumero(dto.getDificultad());
		return dr;
	}

	private DificultadRecetaDto newBuilderToDto(Dificultad model) {
		DificultadRecetaDto dto = new DificultadRecetaDto();
		dto.setId(model.getId());
		dto.setDificultad(model.getNumero());
		return dto;
	}
	
	@Override
	public DificultadRecetaDto builderToDto(Dificultad model) {
		return newBuilderToDto(model);
	}


	@Override
	public List<DificultadRecetaDto> builderListToDto(List<Dificultad> list) {
		List<DificultadRecetaDto> listDto = new ArrayList<>();
		
		for (Dificultad model : list) {
			listDto.add(newBuilderToDto(model));
		}
		
		return listDto;
	}


}
