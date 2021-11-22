package vvr.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vvr.dto.UtensilioDto;
import vvr.model.Utensilio;

/**
 * UtensilioBuilder
 * @author Leo
 *
 */
@Component
public class UtensilioBuilder implements IBuilder <Utensilio, UtensilioDto> {

	/**
	 * Crea una nueva instancia de UtensilioDto dado un UtensilioModel
	 * @param model
	 * @return
	 */
	private UtensilioDto newBuilderToDto(Utensilio model) {
		UtensilioDto dto = new UtensilioDto();
		dto.setId(model.getId());
		dto.setNombre(model.getNombre());
		return dto;
	}

	/**
	 * Crea una nueva instancia de UtensilioModel dado un UtensilioDto
	 * @param dto
	 * @return
	 */
	@Override
	public Utensilio builderToModel(UtensilioDto dto) {
		Utensilio utensilio = new Utensilio();
		utensilio.setId(dto.getId());
		utensilio.setNombre(dto.getNombre());

		return utensilio;
	}

	/**
	 * Crea una nueva instancia de UtensilioDto dado un UtensilioModel
	 */
	@Override
	public UtensilioDto builderToDto(Utensilio model) {
		return newBuilderToDto(model);
	}


	/**
	 * Devuelve una List<UtensilioDto> dado un List<Utensilio> Model 
	 */
	@Override
	public List<UtensilioDto> builderListToDto(List<Utensilio> list) {
		List<UtensilioDto> listDto = new ArrayList<>();
		
		for (Utensilio utensilio : list) {
			listDto.add(newBuilderToDto(utensilio));
		}
		return listDto;
	}

}
