package vvr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vvr.builder.DificultadRecetaBuilder;
import vvr.constant.CodigoRespuestaConstant;
import vvr.constant.ControllerConstant;
import vvr.constant.EndPointConstant;
import vvr.constant.FieldConstant;
import vvr.constant.TipoMetodoConstant;
import vvr.dto.DificultadRecetaDto;
import vvr.dto.response.ResponseDto;
import vvr.dto.response.ResponseErrorDto;
import vvr.dto.response.ResponseOKDto;
import vvr.dto.response.ResponseOKListDto;
import vvr.log.LoggerTemplate;
import vvr.model.Dificultad;
import vvr.service.DificultadRecetaService;
import vvr.validator.commons.CommonsValidator;
import vvr.validator.commons.IValidator;
import vvr.validator.commons.MessageError;

/**
 * Dificultad receta controller
 * 
 * @author Leo
 *
 */
@RequestMapping("/admin/dificultadreceta")
@RestController
public class DificultadRecetaController implements IListController<DificultadRecetaDto>, IValidator<DificultadRecetaDto> {

	static final Logger logger = Logger.getLogger(DificultadRecetaController.class);

	@Autowired
	private DificultadRecetaService drService;

	@Autowired
	private DificultadRecetaBuilder drBuilder;
	
	@Override
	public List<String> validator(DificultadRecetaDto dto) {
		List <String> mensajes = new ArrayList<>();
		//fields validator
		CommonsValidator.setMessageErrorsList(mensajes, 
				CommonsValidator.empty(dto.getDificultad(), FieldConstant.DIFICULTAD));
		//modify
		if(dto.getId() != null) {
				CommonsValidator.setMessageErrorsList(mensajes, 
						CommonsValidator.empty(dto.getId(), FieldConstant.ID));
		}
		return mensajes;
	}

	@Override
	public ResponseDto findOne(DificultadRecetaDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.DIFICULTAD_RECETA_CONTROLLER, EndPointConstant.FIND_ANY));
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.DIFICULTAD_RECETA_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR,
					mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_ID));

			// DificultadReceta
			Optional<Dificultad> value = drService.findById(dto.getId());
			if(value.isPresent()) {
				Dificultad dReceta = value.get();
				
				// Builder Model to Dto
				DificultadRecetaDto dRecetaDto = drBuilder.builderToDto(dReceta);
				
				// logger response
				logger.info(LoggerTemplate.responseOK());
				// return
				return new ResponseOKDto<DificultadRecetaDto>(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, dRecetaDto);
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
	public ResponseDto findAny(DificultadRecetaDto dto) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}

	@Override
	public ResponseDto findAll() {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.DIFICULTAD_RECETA_CONTROLLER, EndPointConstant.FIND_ALL));
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_ALL));

			// List
			List<Dificultad> listDReceta = (ArrayList<Dificultad>) drService.findAll();
			
			//Build Model List to Dto List
			List<DificultadRecetaDto> listDRecetaDto = drBuilder.builderListToDto(listDReceta);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKListDto<DificultadRecetaDto>(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, listDRecetaDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			List<String> mensajes = new ArrayList<>();
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR,
					mensajes);
		}
	}

}
