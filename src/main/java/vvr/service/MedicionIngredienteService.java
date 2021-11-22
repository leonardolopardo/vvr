package vvr.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.MedicionIngrediente;

@Transactional
public interface MedicionIngredienteService extends CrudRepository<MedicionIngrediente, Long> {

}
