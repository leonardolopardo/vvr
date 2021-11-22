package vvr.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.Utensilio;

@Transactional
public interface UtensilioService extends CrudRepository<Utensilio, Long> {

}
