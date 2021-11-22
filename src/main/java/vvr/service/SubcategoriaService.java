package vvr.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.Subcategoria;

@Transactional
public interface SubcategoriaService extends CrudRepository<Subcategoria, Long> {

}
