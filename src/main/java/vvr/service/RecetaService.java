package vvr.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.Receta;

@Transactional
public interface RecetaService extends CrudRepository<Receta, Long> {
	

}