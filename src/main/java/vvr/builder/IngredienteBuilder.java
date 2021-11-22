package vvr.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vvr.dto.IngredienteDto;
import vvr.model.Ingrediente;

/**
 * IngredienteBuilder
 * 
 * @author Leo
 *
 */
@Component
public class IngredienteBuilder implements IBuilder<Ingrediente, IngredienteDto> {

	/**
	 * Transforma un IngredienteDto a un Ingrediente
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public Ingrediente builderToModel(IngredienteDto dto) {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(dto.getId());
		ingrediente.setNombre(dto.getNombre());
		
		return ingrediente;
	}
	
	@Override
	public IngredienteDto builderToDto(Ingrediente model) {
		IngredienteDto dto = new IngredienteDto();
		dto.setId(model.getId());
		dto.setNombre(model.getNombre());
		//dto.setMedicionIngrediente(model.getMedicionIngrediente());
		
		return dto;
	}
	
	public Ingrediente builder(Long id) {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(id);
		return ingrediente;
	}

	@Override
	public List<IngredienteDto> builderListToDto(List<Ingrediente> list) {
		List<IngredienteDto> listDtos = new ArrayList<>();
		
		for (Ingrediente ingrediente : list) {
			IngredienteDto dto = new IngredienteDto();
			dto.setId(ingrediente.getId());
			dto.setNombre(ingrediente.getNombre());
			
			listDtos.add(dto);
		}
		return listDtos;
	}

}
