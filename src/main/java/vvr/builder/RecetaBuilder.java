package vvr.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vvr.dto.IngredienteDto;
import vvr.dto.RecetaDto;
import vvr.dto.SecuenciaRecetaDto;
import vvr.dto.UtensilioDto;
import vvr.model.Ingrediente;
import vvr.model.Receta;
import vvr.model.SecuenciaReceta;
import vvr.model.Utensilio;

/**
 * RecetaBuilder
 * 
 * @author Leo
 *
 */
@Component
public class RecetaBuilder implements IBuilder<Receta, RecetaDto> {

	@Autowired
	private DificultadRecetaBuilder dificultadBuilder;

	@Autowired
	private IngredienteBuilder ingredienteBuilder;

	@Autowired
	private SecuenciaBuilder secuenciaBuilder;

	@Autowired
	private UtensilioBuilder utensilioBuilder;

	/**
	 * Transforma una RecetaDto a una Receta
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public Receta builderToModel(RecetaDto dto) {
		Receta receta = new Receta();
		receta.setId(dto.getId());
		receta.setDificultad(dificultadBuilder.builderToModel(dto.getDificultad()));
		receta.setNombre(dto.getNombre());
		receta.setTiempoCoccion(dto.getTiempoCoccion());
		receta.setTiempoPreparacion(dto.getTiempoPreparacion());

		// Ingrediente
		Set<Ingrediente> ingredientes = new HashSet<>();
		for (IngredienteDto ingredienteDto : dto.getIngredientes()) {
			ingredientes.add(ingredienteBuilder.builderToModel(ingredienteDto));
		}

		// SecuencuaReceta
		Set<SecuenciaReceta> secuencia = new HashSet<>();
		for (SecuenciaRecetaDto secuenciaDto : dto.getSecuencia()) {
			secuencia.add(secuenciaBuilder.builderToModel(secuenciaDto));
		}
		receta.setSecuencia(secuencia);

		// Utensilios
		Set<Utensilio> utensilios = new HashSet<>();
		for (UtensilioDto utensiliosDto : dto.getUtensilios()) {
			utensilios.add(utensilioBuilder.builderToModel(utensiliosDto));
		}
		receta.setUtensilios(utensilios);

		return receta;
	}

	@Override
	public RecetaDto builderToDto(Receta model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecetaDto> builderListToDto(List<Receta> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
