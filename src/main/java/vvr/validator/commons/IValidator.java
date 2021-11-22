package vvr.validator.commons;

import java.util.List;

/**
 * Valida los campos obligatorios
 * @param dto
 * @return
 */
public interface IValidator<T> {
	/**
	 * Valida los campos obligatorios de un DTO
	 * @param dto
	 * @return
	 */
	List<String> validator(T dto);
	
	/**
	 * Valida el ID
	 * @param id
	 * @return
	 */
	static List<String> validator (Long id) {
		return CommonsValidator.validatorById(id);
	}
}
