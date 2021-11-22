package vvr.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vvr.dto.CategoriaRecetaDto;
import vvr.dto.SubcategoriaRecetaDto;
import vvr.model.Subcategoria;
import vvr.validator.commons.MessageError;

/**
 * SubcaterogiaReceta
 * @author Leo
 *
 */
@Component
public class SubcategoriaBuilder implements IBuilder<Subcategoria, SubcategoriaRecetaDto> {

	@Autowired
	private CategoriaRecetaBuilder crBuilder;
	
	/**
	 * Crea un Set<CategoriaRecetaDto> dado una SubcategoriaReceta  
	 * @param model
	 * @return
	 */
	private Set<CategoriaRecetaDto> setCategoriaRecetaDto (Subcategoria model) {
		CategoriaRecetaDto crDto = crBuilder.builderToDto(model.getCategoria());
		Set<CategoriaRecetaDto> setCrDto = new HashSet<>();
		setCrDto.add(crDto);
		return setCrDto;
	}
	
	/**
	 * Create una instancia de SubcategoriaReceta
	 * @param dto
	 * @return
	 */
	private Subcategoria newBuilderToModel(SubcategoriaRecetaDto dto) {
		Subcategoria subcategoriaReceta = new Subcategoria();
		subcategoriaReceta.setId(dto.getId());
		subcategoriaReceta.setNombre(dto.getSubcategoria());
		for (CategoriaRecetaDto categoriaRecetaDto : dto.getCategoria()) {
			subcategoriaReceta.setCategoria(crBuilder.builderToModel(categoriaRecetaDto));
		}
		return subcategoriaReceta;
	}
	
	@Override
	public Subcategoria builderToModel(SubcategoriaRecetaDto dto) {
		return newBuilderToModel(dto);
	}

	@Override
	public SubcategoriaRecetaDto builderToDto(Subcategoria model) {
		SubcategoriaRecetaDto dto = new SubcategoriaRecetaDto();
		dto.setId(model.getId());
		dto.setSubcategoria(model.getNombre());
		dto.setCategoria(setCategoriaRecetaDto(model));
		return dto;
	}

	@Override
	public List<SubcategoriaRecetaDto> builderListToDto(List<Subcategoria> list) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}
	
	public Set<Subcategoria> builderSetToModel (Set<SubcategoriaRecetaDto> setDto) {
		Set<Subcategoria> setModel = new HashSet<>();
		
		for (SubcategoriaRecetaDto dto : setDto) {
			setModel.add(newBuilderToModel(dto));
		}
		
		return setModel;
	}

}
