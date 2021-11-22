package vvr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vvr.builder.UtensilioBuilder;
import vvr.constant.CodigoRespuestaConstant;
import vvr.constant.ControllerConstant;
import vvr.constant.EndPointConstant;
import vvr.constant.FieldConstant;
import vvr.constant.TipoMetodoConstant;
import vvr.dto.UtensilioDto;
import vvr.dto.response.ResponseDto;
import vvr.dto.response.ResponseErrorDto;
import vvr.dto.response.ResponseOKDto;
import vvr.dto.response.ResponseOKListDto;
import vvr.log.LoggerTemplate;
import vvr.model.Utensilio;
import vvr.service.UtensilioService;
import vvr.validator.commons.CommonsValidator;
import vvr.validator.commons.IValidator;
import vvr.validator.commons.MessageError;

/**
 * Utensilio controller
 * 
 * @author Leo
 *
 */
@RequestMapping("/admin/utensilio")
@RestController
public class UtensilioController implements IABMController<UtensilioDto>, IListController<UtensilioDto>, IValidator<UtensilioDto> {

	static final Logger logger = Logger.getLogger(UtensilioController.class);

	@Autowired
	private UtensilioService utensilioService;

	@Autowired
	private UtensilioBuilder utensilioBuilder;
	
	/**
	 * Agregar o modificar un Utensilio
	 * @param dto
	 * @param endPointMethod
	 * @return
	 */
	private ResponseDto save (UtensilioDto dto, String endPointMethod) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.UTENSILIO_CONTROLLER, endPointMethod));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.UTENSILIO_VALIDATOR));
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
			Utensilio utensilio = utensilioBuilder.builderToModel(dto);
			// save
			utensilioService.save(utensilio);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<UtensilioDto>(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, dto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@Override
	public List<String> validator(UtensilioDto dto) {
		List <String> mensajes = new ArrayList<>();
		//fields validator
		CommonsValidator.setMessageErrorsList(mensajes, 
				CommonsValidator.empty(dto.getNombre(), FieldConstant.NOMBRE));
		//modify
		if(dto.getId() != null) {
				CommonsValidator.setMessageErrorsList(mensajes, 
						CommonsValidator.empty(dto.getId(), FieldConstant.ID));
		}
		return mensajes;
	}

	@Override
	public ResponseDto findOne(UtensilioDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.UTENSILIO_CONTROLLER, EndPointConstant.FIND_ONE));
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.UTENSILIO_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_ID));

			// List
			Optional<Utensilio> value = utensilioService.findById(dto.getId());
			
			if(value.isPresent()) {
				Utensilio utensilio = value.get();
				
				// Builder Model to Dto
				UtensilioDto utensilioDto = utensilioBuilder.builderToDto(utensilio);
				
				// logger response
				logger.info(LoggerTemplate.responseOK());
				
				// return
				return new ResponseOKDto<UtensilioDto>(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, utensilioDto);
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
	public ResponseDto findAny(UtensilioDto dto) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}
	
	@Override
	public ResponseDto findAll () {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.UTENSILIO_CONTROLLER, EndPointConstant.FIND_ALL));
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_ALL));

			// List
			List<Utensilio> listUtensilios = (ArrayList<Utensilio>) utensilioService.findAll();
			
			//Build Model List to Dto List
			List<UtensilioDto> listUtensiliosDto = utensilioBuilder.builderListToDto(listUtensilios);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKListDto<UtensilioDto>(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, listUtensiliosDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			List<String> mensajes = new ArrayList<>();
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}
	
	@Override
	public ResponseDto add(UtensilioDto dto) {
		return save(dto, EndPointConstant.ADD);
	}

	@Override
	public ResponseDto modify(UtensilioDto dto) {
		return save(dto, EndPointConstant.MODIFY);
	}

	@Override
	public ResponseDto deleteById(UtensilioDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.UTENSILIO_CONTROLLER, EndPointConstant.DELETE));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.UTENSILIO_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());

		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR,
					mensajes);
		}

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));

			// builder
			Utensilio utensilio = utensilioBuilder.builderToModel(dto);
			// save
			utensilioService.delete(utensilio);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<UtensilioDto>(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

}
