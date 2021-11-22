package vvr.builder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vvr.dto.DificultadRecetaDto;
import vvr.model.Dificultad;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DificultadRecetaBuilder.class })
public class DificultadBuildertTest {
	
	@Autowired
	DificultadRecetaBuilder dificultadRecetaBuilder;
	
	private DificultadRecetaDto initDificultadRecetaDto () {
		DificultadRecetaDto dto = new DificultadRecetaDto();
		dto.setId(1L);
		dto.setDificultad(1);
		return dto;
	}
	
	private Dificultad initDificultadRecetaModel() {
		Dificultad model = new Dificultad();
		model.setId(1L);
		model.setNumero(1);
		return model;
	}
	
	@Test
	public void builderToModel () {
		DificultadRecetaDto dto = initDificultadRecetaDto();
		
		Dificultad dReceta = dificultadRecetaBuilder.builderToModel(dto);
		
		assertEquals(dReceta.getId(), Long.valueOf(1L));
		assertEquals(dReceta.getNumero(), Integer.valueOf(1));
	}
	
	@Test
	public void builderToDto () {
		Dificultad model = initDificultadRecetaModel();
		
		DificultadRecetaDto dto =  dificultadRecetaBuilder.builderToDto(model);
		
		assertEquals(dto.getId(), Long.valueOf(1L));
		assertEquals(dto.getDificultad(), Integer.valueOf(1));
	}
	
	@Test
	public void builderListToDto () {
		Dificultad model = initDificultadRecetaModel();
		
		List<Dificultad> list = new ArrayList<>();
		list.add(model);
		
		List<DificultadRecetaDto> listDto = dificultadRecetaBuilder.builderListToDto(list);
		
		DificultadRecetaDto dto = listDto.get(0);
		assertEquals(dto.getId(), Long.valueOf(1L));
		assertEquals(dto.getDificultad(), Integer.valueOf(1));
	}

}
