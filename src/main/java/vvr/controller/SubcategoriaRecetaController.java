package vvr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vvr.builder.SubcategoriaBuilder;
import vvr.constant.CodigoRespuestaConstant;
import vvr.constant.ControllerConstant;
import vvr.constant.EndPointConstant;
import vvr.constant.FieldConstant;
import vvr.constant.TipoMetodoConstant;
import vvr.dto.IngredienteDto;
import vvr.dto.SubcategoriaRecetaDto;
import vvr.dto.response.ResponseDto;
import vvr.dto.response.ResponseErrorDto;
import vvr.dto.response.ResponseOKDto;
import vvr.log.LoggerTemplate;
import vvr.model.Subcategoria;
import vvr.service.SubcategoriaService;
import vvr.validator.commons.CommonsValidator;
import vvr.validator.commons.IValidator;

/**
 * 
 * @author Leo
 *
 */
@RequestMapping("/admin/subcategoria")
@RestController
public class SubcategoriaRecetaController implements IABMController<SubcategoriaRecetaDto>,	IValidator<SubcategoriaRecetaDto> {

	static final Logger logger = Logger.getLogger(SubcategoriaRecetaController.class);
	
	@Autowired
	private SubcategoriaService subcategoriaService;

	@Autowired
	private SubcategoriaBuilder subcategoriaBuilder;
	
	/**
	 * Agregar o modificar una SubcategoriaReceta
	 * @param dto
	 * @param endPointMethod
	 * @return
	 */
	private ResponseDto save (SubcategoriaRecetaDto dto, String endPointMethod) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.SUBCATEGORIA_CONTROLLER, endPointMethod));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.SUBCATEGORIA_VALIDATOR));
		
		// validator
		List<String> mensajes = validator(dto);
		
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR,	mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_SAVE));

			// builder
			Subcategoria subcategoriaReceta = subcategoriaBuilder.builderToModel(dto);
			
			// save
			subcategoriaService.save(subcategoriaReceta);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<IngredienteDto>(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}
	
	@Override
	public List<String> validator(SubcategoriaRecetaDto dto) {
		List <String> mensajes = new ArrayList<>();
		//fields validator
		CommonsValidator.setMessageErrorsList(mensajes,
					CommonsValidator.empty(dto.getSubcategoria(), FieldConstant.SUBCATEGORIA),
					CommonsValidator.empty(dto.getSubcategoria(), FieldConstant.CATEGORIA)
				);
		//modify
		if(dto.getId() != null) {
			CommonsValidator.setMessageErrorsList(mensajes,
					CommonsValidator.empty(dto.getId(), FieldConstant.ID)
				);
		}	
		return mensajes;
	}


	@Override
	public ResponseDto add(SubcategoriaRecetaDto dto) {
		return save(dto, EndPointConstant.ADD);
	}

	@Override
	public ResponseDto modify(SubcategoriaRecetaDto dto) {
		return save(dto, EndPointConstant.MODIFY);
	}

	@Override
	public ResponseDto deleteById(SubcategoriaRecetaDto dto) {
		// logger controller
		logger.info(
		LoggerTemplate.inicialize(ControllerConstant.SUBCATEGORIA_CONTROLLER, EndPointConstant.DELETE));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.SUBCATEGORIA_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());

		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));

			// builder
			Subcategoria subCategoriaReceta = subcategoriaBuilder.builderToModel(dto);
			
			// delete
			subcategoriaService.delete(subCategoriaReceta);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<SubcategoriaRecetaDto>(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}
}
