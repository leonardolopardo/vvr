package vvr.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vvr.model.Receta;

@Repository
@Transactional(readOnly = true)
public class RecetaRepositoryImpl implements RecetaRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void customSave(Receta receta) {
		 
		/*Query query = entityManager.createNativeQuery("SELECT em.* FROM spring_data_jpa_example.employee as em " +
	                "WHERE em.firstname LIKE ?", Employee.class);
	        query.setParameter(1, firstName + "%");

	        return query.getResultList();
	        */
	}

}
