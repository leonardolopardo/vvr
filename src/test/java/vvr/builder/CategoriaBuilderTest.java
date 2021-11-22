package vvr.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CategoriaRecetaBuilder.class })
public class CategoriaBuilderTest {
	
	@Autowired
	CategoriaRecetaBuilder crBuilder;
	
	@Test
	public void builderToModel () {
//		Set<CategoriaRecetaDto> setCategoriaRecetaDto = new HashSet<>();
//		CategoriaRecetaDto dto = new CategoriaRecetaDto();
//		setCategoriaRecetaDto.add(dto);
//		
//		dto.setId(1L);
//		Set<SubcategoriaRecetaDto> subcategorias = new HashSet<>();
//		SubcategoriaRecetaDto subcategoriaRecetaDto = new SubcategoriaRecetaDto();
//		subcategoriaRecetaDto.setId(1L);
//		subcategoriaRecetaDto.setCategoria(dto);
//		subcategorias.add(subcategoriaRecetaDto)
//		dto.setSubcategorias(subcategorias);
		
	}
	
	
	
	
	

}
