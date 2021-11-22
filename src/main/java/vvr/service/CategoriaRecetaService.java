package vvr.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.Categoria;

@Transactional
public interface CategoriaRecetaService extends CrudRepository<Categoria, Long> {

}
