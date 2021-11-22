package vvr.builder;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vvr.dto.CategoriaRecetaDto;
import vvr.model.Categoria;
import vvr.model.Subcategoria;
import vvr.validator.commons.MessageError;

@Component
public class CategoriaRecetaBuilder implements IBuilder<Categoria, CategoriaRecetaDto> {

	@Autowired
	private SubcategoriaBuilder subcategoriaBuilder;
	
	/**
	 * set a SubCategoriaReceta
	 * @param CategoriaRecetaDto dto
	 * @return
	 */
	private Set<Subcategoria> setSubCategoriaReceta (CategoriaRecetaDto dto) {
		return subcategoriaBuilder.builderSetToModel(dto.getSubcategorias());
	}
	
	@Override
	public Categoria builderToModel(CategoriaRecetaDto dto) {
		Categoria cr = new Categoria();
		cr.setId(dto.getId());
		cr.setNombre(dto.getCategoria());
//		cr.setSubcategorias(setSubCategoriaReceta(dto));
		
		return cr;
	}

	@Override
	public CategoriaRecetaDto builderToDto(Categoria model) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}

	@Override
	public List<CategoriaRecetaDto> builderListToDto(List<Categoria> list) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}

}
