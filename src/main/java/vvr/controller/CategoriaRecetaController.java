package vvr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vvr.builder.CategoriaRecetaBuilder;
import vvr.constant.CodigoRespuestaConstant;
import vvr.constant.ControllerConstant;
import vvr.constant.EndPointConstant;
import vvr.constant.FieldConstant;
import vvr.constant.TipoMetodoConstant;
import vvr.dto.CategoriaRecetaDto;
import vvr.dto.response.ResponseDto;
import vvr.dto.response.ResponseErrorDto;
import vvr.dto.response.ResponseOKDto;
import vvr.log.LoggerTemplate;
import vvr.model.Categoria;
import vvr.service.CategoriaRecetaService;
import vvr.validator.commons.CommonsValidator;
import vvr.validator.commons.IValidator;

/**
 * Categoria receta
 * 
 * @author Leo
 *
 */
@RequestMapping("/admin/categoriareceta")
@RestController
public class CategoriaRecetaController implements IABMController<CategoriaRecetaDto>, IValidator<CategoriaRecetaDto> {

	static final Logger logger = Logger.getLogger(CategoriaRecetaController.class);

	@Autowired
	private CategoriaRecetaService crService;

	@Autowired
	private CategoriaRecetaBuilder crBuilder;
	
	/**
	 * Agregar o modificar una CategoriaReceta
	 * @param dto
	 * @param endPointMethod
	 * @return
	 */
	private ResponseDto save (CategoriaRecetaDto dto, String endPointMethod) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.CATEGORIA_RECETA_CONTROLLER, endPointMethod));
		
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.CATEGORIA_RECETA_VALIDATOR));
		
		// validator
		List<String> mensajes = validator(dto);
		
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_SAVE));

			// builder dto to model
			Categoria cr = crBuilder.builderToModel(dto);
			// save
			Categoria crSaved = crService.save(cr);
			// builder model saved to dto
			CategoriaRecetaDto crDto = crBuilder.builderToDto(crSaved);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<CategoriaRecetaDto>(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, crDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@Override
	public List<String> validator(CategoriaRecetaDto dto) {
		List <String> mensajes = new ArrayList<>();
		//fields validator
		CommonsValidator.setMessageErrorsList(mensajes, 
				CommonsValidator.empty(dto.getCategoria(), FieldConstant.CATEGORIA));
		//modify
		if(dto.getId() != null) {
				CommonsValidator.setMessageErrorsList(mensajes, 
						CommonsValidator.empty(dto.getId(), FieldConstant.ID));
		}
		return mensajes;
	}


	@Override
	public ResponseDto add(CategoriaRecetaDto dto) {
		return save(dto, EndPointConstant.ADD);
	}

	@Override
	public ResponseDto modify(CategoriaRecetaDto dto) {
		return save(dto, EndPointConstant.MODIFY);
	}
	
	@Override
	public ResponseDto deleteById(CategoriaRecetaDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.CATEGORIA_RECETA_CONTROLLER, EndPointConstant.DELETE));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.CATEGORIA_RECETA_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());

		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));
			// builder
			Categoria cr = crBuilder.builderToModel(dto);
			// delete
			crService.delete(cr);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<CategoriaRecetaDto>(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

}
