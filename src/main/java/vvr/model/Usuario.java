package vvr.model;

import java.util.List;

/**
 * 
 * @author Leo
 *
 */
//@Entity
//@Table(name = "usuario")
public class Usuario {
	
	private List <Receta> recetas;

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
	

}
