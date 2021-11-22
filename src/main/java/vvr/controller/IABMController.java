package vvr.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vvr.dto.response.ResponseDto;

/**
 * Interfase ABM Controller
 * @author Leo
 *
 * @param <T>
 */
public interface IABMController <T> {
	/**
	 * Add <T>
	 * @param dto
	 * @return
	 * See path in Interface
	 */
	@PostMapping(value = "/add")
	ResponseDto add (@RequestBody T dto);
	
	/**
	 * Modify <T>
	 * @param dto
	 *  * See path in Interface
	 * @return
	 */
	@PostMapping(value = "/modify")
	ResponseDto modify (@RequestBody T dto);
	
	/**
	 * Delete <T>
	 * See path in Interface
	 */
	@PostMapping(value = "/delete")
	ResponseDto deleteById (@RequestBody T dto);

}
