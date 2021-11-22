package vvr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vvr.builder.IngredienteBuilder;
import vvr.constant.CodigoRespuestaConstant;
import vvr.constant.ControllerConstant;
import vvr.constant.EndPointConstant;
import vvr.constant.FieldConstant;
import vvr.constant.TipoMetodoConstant;
import vvr.dto.IngredienteDto;
import vvr.dto.response.ResponseDto;
import vvr.dto.response.ResponseErrorDto;
import vvr.dto.response.ResponseOKDto;
import vvr.dto.response.ResponseOKListDto;
import vvr.log.LoggerTemplate;
import vvr.model.Ingrediente;
import vvr.service.IngredienteService;
import vvr.validator.commons.CommonsValidator;
import vvr.validator.commons.IValidator;
import vvr.validator.commons.MessageError;

/**
 * Ingrediente controller
 * 
 * @author Leo
 *
 */
@RequestMapping("/admin/ingrediente")
@RestController
public class IngredienteController implements IABMController<IngredienteDto>, IListController<IngredienteDto>,  IValidator<IngredienteDto> {

	static final Logger logger = Logger.getLogger(IngredienteController.class);

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired  
	private IngredienteBuilder ingredienteBuilder;
	
	
	/**
	 * Agregar o modificar una Ingrediente
	 * @param dto
	 * @param endPointMethod
	 * @return
	 */
	private ResponseDto save (IngredienteDto dto, String endPointMethod) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.INGREDIENTE_CONTROLLER, endPointMethod));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.INGREDIENTE_VALIDATOR));
		// validator
		List<String> mensajes = new ArrayList<>();
		if(EndPointConstant.MODIFY.equals(endPointMethod)) {
			mensajes.addAll(IValidator.validator(dto.getId()));
		}
		mensajes.addAll(validator(dto));
		
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR,	mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_SAVE));

			// builder
			Ingrediente ingrediente = ingredienteBuilder.builderToModel(dto);
			// save
			ingredienteService.save(ingrediente);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<IngredienteDto>(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, dto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@Override
	public List<String> validator(IngredienteDto dto) {
		List <String> mensajes = new ArrayList<>();
		//fields validator
		CommonsValidator.setMessageErrorsList(mensajes,
					CommonsValidator.empty(dto.getNombre(), FieldConstant.NOMBRE)
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
	public ResponseDto findOne(IngredienteDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.INGREDIENTE_CONTROLLER, EndPointConstant.FIND_ONE));
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.INGREDIENTE_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_ID));

			// Get model object
			Optional<Ingrediente> value = ingredienteService.findById(dto.getId());
			if(value.isPresent()) {
				Ingrediente ingrediente = value.get();
				
				// Builder Model to Dto
				IngredienteDto ingredienteDto = ingredienteBuilder.builderToDto(ingrediente);
				
				// logger response
				logger.info(LoggerTemplate.responseOK());
				// return
				return new ResponseOKDto<IngredienteDto>(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, ingredienteDto);
			} else {
				mensajes.add(CommonsValidator.elementNotFound());
				return new ResponseErrorDto(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
			}
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@Override
	public ResponseDto findAny(IngredienteDto dto) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}

	@Override
	public ResponseDto findAll() {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.INGREDIENTE_CONTROLLER, EndPointConstant.FIND_ALL));
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_ALL));

			// List
			List<Ingrediente> ingredientes = (ArrayList<Ingrediente>) ingredienteService.findAll();
			
			//Build Model List to Dto List
			List<IngredienteDto> ingredientesDto = ingredienteBuilder.builderListToDto(ingredientes);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKListDto<IngredienteDto>(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, ingredientesDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			List<String> mensajes = new ArrayList<>();
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@Override
	public ResponseDto add(IngredienteDto dto) {
		return save(dto, EndPointConstant.ADD);
	}

	@Override
	public ResponseDto modify(IngredienteDto dto) {
		return save(dto, EndPointConstant.MODIFY);
	}

	@Override
	public ResponseDto deleteById(IngredienteDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.INGREDIENTE_CONTROLLER, EndPointConstant.DELETE));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.INGREDIENTE_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());

		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));

			// builder
			Ingrediente ingrediente = ingredienteBuilder.builder(dto.getId());
			// delete
			ingredienteService.delete(ingrediente);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<IngredienteDto>(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

}
