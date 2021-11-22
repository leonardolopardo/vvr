package vvr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vvr.builder.RecetaBuilder;
import vvr.constant.CodigoRespuestaConstant;
import vvr.constant.ControllerConstant;
import vvr.constant.EndPointConstant;
import vvr.constant.FieldConstant;
import vvr.constant.TipoMetodoConstant;
import vvr.dto.RecetaDto;
import vvr.dto.response.ResponseDto;
import vvr.dto.response.ResponseErrorDto;
import vvr.dto.response.ResponseOKDto;
import vvr.dto.response.ResponseOKListDto;
import vvr.log.LoggerTemplate;
import vvr.model.Receta;
import vvr.repository.RecetaRepository;
import vvr.service.RecetaService;
import vvr.validator.commons.CommonsValidator;
import vvr.validator.commons.IValidator;
import vvr.validator.commons.MessageError;

/**
 * Controller para Receta
 * 
 * @author Leo
 *
 */
@RequestMapping("/user/receta")
@RestController
public class RecetaController implements IABMController<RecetaDto>, IListController<RecetaDto>, IValidator<RecetaDto> {

	static final Logger logger = Logger.getLogger(RecetaController.class);

	@Autowired
	private RecetaService recetaService;
	
	@Autowired
	private RecetaRepository recetaRepository;

	@Autowired
	private RecetaBuilder recetaBuilder;
	
	/**
	 * Agregar o modificar una Receta
	 * @param dto
	 * @param endPointMethod
	 * @return
	 */
	private ResponseDto save (RecetaDto dto, String endPointMethod) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.RECETA_CONTROLLER, endPointMethod));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.RECETA_VALIDATOR));
		
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
			Receta receta = recetaBuilder.builderToModel(dto);
			// save
			recetaRepository.customSave(receta);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			
			// return
			return new ResponseOKDto<RecetaDto>(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	} 

	@Override
	public List<String> validator(RecetaDto dto) {
		List <String> mensajes = new ArrayList<>();
		//fields validator
		CommonsValidator.setMessageErrorsList(mensajes,
					CommonsValidator.empty(dto.getNombre(), FieldConstant.NOMBRE), 
					CommonsValidator.empty(dto.getTiempoCoccion(), FieldConstant.TIEMPO_COCCION),
					CommonsValidator.empty(dto.getTiempoPreparacion(), FieldConstant.TIEMPO_PREPARACION)
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
	public ResponseDto findOne(RecetaDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.RECETA_CONTROLLER, EndPointConstant.FIND_ONE));
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.RECETA_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_ID));

			// Get model object
			Optional<Receta> value = recetaService.findById(dto.getId());
			if(value.isPresent()) {
				Receta receta = value.get();
				
				// Builder Model to Dto
				RecetaDto recetaDto = recetaBuilder.builderToDto(receta);
				
				// logger response
				logger.info(LoggerTemplate.responseOK());
				// return
				return new ResponseOKDto<RecetaDto>(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, recetaDto);
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
	public ResponseDto findAny(RecetaDto dto) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}

	@Override
	public ResponseDto findAll() {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.RECETA_CONTROLLER, EndPointConstant.FIND_ALL));
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_ALL));

			// List
			List<Receta> listReceta = (ArrayList<Receta>) recetaService.findAll();
			
			//Build Model List to Dto List
			List<RecetaDto> listRecetaDto = recetaBuilder.builderListToDto(listReceta);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKListDto<RecetaDto>(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, listRecetaDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			List<String> mensajes = new ArrayList<>();
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@Override
	public ResponseDto add(RecetaDto dto) {
		return save(dto, EndPointConstant.ADD);
	}

	@Override
	public ResponseDto modify(RecetaDto dto) {
		return save(dto, EndPointConstant.MODIFY);
	}

	@Override
	public ResponseDto deleteById(RecetaDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.RECETA_CONTROLLER, EndPointConstant.DELETE));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.RECETA_VALIDATOR));
		
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());

		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));

			// builder
			Receta receta = recetaBuilder.builderToModel(dto);
			// save
			recetaService.delete(receta);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<RecetaDto>(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

}
