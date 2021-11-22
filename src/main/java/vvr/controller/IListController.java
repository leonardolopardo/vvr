package vvr.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vvr.dto.response.ResponseDto;

/**
 * Interfase para listados
 * @author Leo
 *
 * @param <T>
 */
public interface IListController<T> {
	/**
	 * FindOne <T>
	 * @param dto
	 * @return
	 */
	@PostMapping(value = "/findOne")
	ResponseDto findOne (@RequestBody T dto);
	
	/**
	 * FindAny  <T>
	 * @param dto
	 * @return
	 */
	@PostMapping(value = "/findAny")
	ResponseDto findAny (@RequestBody T dto);

	/**
	 * FindAll <T>
	 * @return
	 */
	@PostMapping(value = "/findAll")
	ResponseDto findAll ();
}
