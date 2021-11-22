package vvr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vvr.builder.MedicionIngredienteBuilder;
import vvr.constant.CodigoRespuestaConstant;
import vvr.constant.ControllerConstant;
import vvr.constant.EndPointConstant;
import vvr.constant.FieldConstant;
import vvr.constant.TipoMetodoConstant;
import vvr.dto.MedicionIngredienteDto;
import vvr.dto.response.ResponseDto;
import vvr.dto.response.ResponseErrorDto;
import vvr.dto.response.ResponseOKDto;
import vvr.dto.response.ResponseOKListDto;
import vvr.log.LoggerTemplate;
import vvr.model.MedicionIngrediente;
import vvr.service.MedicionIngredienteService;
import vvr.validator.commons.CommonsValidator;
import vvr.validator.commons.IValidator;
import vvr.validator.commons.MessageError;

/**
 * Medicion ingrediente
 * 
 * @author Leo
 *
 */
@RequestMapping("/admin/medicioningrediente")
@RestController
public class MedicionIngredienteController
		implements IABMController<MedicionIngredienteDto>, IListController<MedicionIngredienteDto>, IValidator<MedicionIngredienteDto> {

	static final Logger logger = Logger.getLogger(MedicionIngredienteController.class);

	@Autowired
	private MedicionIngredienteService miService;

	@Autowired
	private MedicionIngredienteBuilder miBuilder;
	
	private ResponseDto save (MedicionIngredienteDto dto, String endPointMethod) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.MEDICION_INGREDIENTE_CONTROLLER, endPointMethod));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.MEDICION_INGREDIENTE_VALIDATOR));
		// validator
		List<String> mensajes = validator(dto);
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR,
					mensajes);
		}

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_SAVE));

			// builder
			MedicionIngrediente mi = miBuilder.builderToModel(dto);
			// save
			miService.save(mi);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<MedicionIngredienteDto>(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, dto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(endPointMethod, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR,	mensajes);
		}
	}

	@Override
	public List<String> validator(MedicionIngredienteDto dto) {
		List <String> mensajes = new ArrayList<>();
		//fields validator
		CommonsValidator.setMessageErrorsList(mensajes,
					CommonsValidator.empty(dto.getUnidadMedida(), FieldConstant.UNIDAD_MEDIDA), 
					CommonsValidator.empty(dto.getNomenclatura(), FieldConstant.NOMENCLATURA)
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
	public ResponseDto findOne(MedicionIngredienteDto dto) {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.MEDICION_INGREDIENTE_CONTROLLER, EndPointConstant.FIND_ONE));
		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.MEDICION_INGREDIENTE_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());
		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_BY_ID));

			// MedicionIngrediente
			Optional<MedicionIngrediente> value = miService.findById(dto.getId());
			if(value.isPresent()) {
				MedicionIngrediente mi = value.get();
				
				// Build Model to Dto
				MedicionIngredienteDto miDto = miBuilder.builderToDto(mi);
				
				// logger response
				logger.info(LoggerTemplate.responseOK());
				// return
				return new ResponseOKDto<MedicionIngredienteDto>(EndPointConstant.FIND_ONE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, miDto);
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
	public ResponseDto findAny(MedicionIngredienteDto dto) {
		throw new NotImplementedException(MessageError.NOT_IMPLEMENTED_EXCEPTION_MESSAGE);
	}

	@Override
	public ResponseDto findAll() {
		// logger controller
		logger.info(LoggerTemplate.inicialize(ControllerConstant.MEDICION_INGREDIENTE_CONTROLLER, EndPointConstant.FIND_ALL));
		
		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_FIND_ALL));

			// List
			List<MedicionIngrediente> listMi = (ArrayList<MedicionIngrediente>) miService.findAll();
			
			//Build Model List to Dto List
			List<MedicionIngredienteDto> listMiDto = miBuilder.builderListToDto(listMi);
			
			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKListDto<MedicionIngredienteDto>(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, listMiDto);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			List<String> mensajes = new ArrayList<>();
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.FIND_ALL, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

	@Override
	public ResponseDto add(MedicionIngredienteDto dto) {
		return save(dto, EndPointConstant.ADD);
	}

	@Override
	public ResponseDto modify(MedicionIngredienteDto dto) {
		return save(dto, EndPointConstant.MODIFY);
	}

	@Override
	public ResponseDto deleteById(MedicionIngredienteDto dto) {
		// logger controller
		logger.info(
		LoggerTemplate.inicialize(ControllerConstant.MEDICION_INGREDIENTE_CONTROLLER, EndPointConstant.DELETE));

		// logger validator
		logger.info(LoggerTemplate.validator(ControllerConstant.MEDICION_INGREDIENTE_VALIDATOR));
		// validator
		List<String> mensajes = IValidator.validator(dto.getId());

		if (!mensajes.isEmpty()) {
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}

		try {
			// logger service
			logger.info(LoggerTemplate.method(ControllerConstant.SERVICE_DELETE));

			// builder
			MedicionIngrediente mi = miBuilder.builderToModel(dto);
			// delete
			miService.delete(mi);

			// logger response
			logger.info(LoggerTemplate.responseOK());
			// return
			return new ResponseOKDto<MedicionIngredienteDto>(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.OK, null);
		} catch (Exception e) {
			String messageException = e.getMessage();
			logger.error(messageException);
			mensajes.add(messageException);
			return new ResponseErrorDto(EndPointConstant.DELETE, TipoMetodoConstant.POST, CodigoRespuestaConstant.ERROR, mensajes);
		}
	}

}
