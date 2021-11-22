package vvr.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.Dificultad;

@Transactional
public interface DificultadRecetaService extends CrudRepository<Dificultad, Long>{

}
