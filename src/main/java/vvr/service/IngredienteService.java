package vvr.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.Ingrediente;

@Transactional
public interface IngredienteService extends CrudRepository<Ingrediente, Long> {

}
