package br.harlan.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.dtos.PointReleasesDto;
import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.entities.PointReleasesEntity;
import br.harlan.api.enums.TypeReleaseEnum;
import br.harlan.api.response.Response;
import br.harlan.api.services.EmployeesService;
import br.harlan.api.services.PointReleasesService;

@RestController
@RequestMapping("/api/point-releases")
@CrossOrigin(origins = "*")
public class PointReleasesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PointReleasesController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	PointReleasesService pointReleasesService;
	@Autowired
	EmployeesService employeesService;

	@Value("${pagination.amount_per_page}")
	private int amountPerPage;

	public PointReleasesController() {
	}

	/**
	 * Lista os lançamentos de um funcionário.
	 * 
	 * @param employeeId
	 * @param page
	 * @param order
	 * @param dir
	 * @return ResponseEntity<Response<Page<PointReleasesDto>>>
	 */
	@GetMapping(value = "/employee/{employeeId}")
	public ResponseEntity<Response<Page<PointReleasesDto>>> pointReleasesByEmployeeId(
			@PathVariable("employeeId") Long employeeId, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		LOGGER.info("Buscando os lançamentos por ID de funcionário: {}, página {}", employeeId, page);

		Response<Page<PointReleasesDto>> response = new Response<Page<PointReleasesDto>>();

		PageRequest pageRequest = new PageRequest(page, amountPerPage, Direction.valueOf(dir), order);
		Page<PointReleasesEntity> pointReleasesEntity = pointReleasesService.findByEmployeeId(employeeId, pageRequest);
		Page<PointReleasesDto> pointReleasesDto = pointReleasesEntity
				.map(pointRelease -> entityToPointReleasesDto(pointRelease));

		response.setData(pointReleasesDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Busca um lançamento por Id.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<PointReleasesDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<PointReleasesDto>> read(@PathVariable("id") Long id) {
		LOGGER.info("Buscando os lançamentos por ID: {}", id);
		Response<PointReleasesDto> response = new Response<PointReleasesDto>();
		Optional<PointReleasesEntity> pOptional = pointReleasesService.findById(id);

		if (!pOptional.isPresent()) {
			LOGGER.info("Lançamento {} não encontrado");
			response.getErrors().add("Lançamento não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(entityToPointReleasesDto(pOptional.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Adiciona um novo lançamento na base de dados.
	 * 
	 * @param pointReleasesDto
	 * @param bindingResult
	 * @return
	 * @throws ParseException
	 */
	@PostMapping
	public ResponseEntity<Response<PointReleasesDto>> create(@Valid @RequestBody PointReleasesDto pointReleasesDto,
			BindingResult bindingResult) throws ParseException {
		LOGGER.info("Adicionando lançamento: {}", pointReleasesDto.toString());
		Response<PointReleasesDto> response = new Response<PointReleasesDto>();

		validateEmployee(pointReleasesDto, bindingResult);
		PointReleasesEntity pointReleaseEntity = dtoToPointReleasesEntity(pointReleasesDto, bindingResult);
		if (bindingResult.hasErrors()) {
			LOGGER.error("Erro ao validar lançamento: {}", bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		pointReleaseEntity = pointReleasesService.create(pointReleaseEntity);
		response.setData(entityToPointReleasesDto(pointReleaseEntity));
		return ResponseEntity.ok(response);
	}

	/**
	 * Atualiza os dados de um lançamento.
	 * 
	 * @param id
	 * @param pointReleasesDto
	 * @param bindingResult
	 * @return ResponseEntity<Response<PointReleasesDto>>
	 * @throws ParseException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<PointReleasesDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody PointReleasesDto pointReleasesDto, BindingResult bindingResult) throws ParseException {
		LOGGER.info("Atualizando lançamento id: {}, lançamento: {}", id, pointReleasesDto.toString());
		Response<PointReleasesDto> response = new Response<PointReleasesDto>();
		validateEmployee(pointReleasesDto, bindingResult);
		pointReleasesDto.setId(Optional.of(id));

		PointReleasesEntity pointReleasesEntity = dtoToPointReleasesEntity(pointReleasesDto, bindingResult);

		if (bindingResult.hasErrors()) {
			LOGGER.error("Erro ao atualizar lançamento: " + bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		pointReleasesEntity = pointReleasesService.create(pointReleasesEntity);
		response.setData(entityToPointReleasesDto(pointReleasesEntity));
		return ResponseEntity.ok(response);
	}

	/**
	 * Deleta um lançamento por Id.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		LOGGER.info("Removendo lançamento, id: {}", id);
		Response<String> response = new Response<String>();
		Optional<PointReleasesEntity> pOptional = pointReleasesService.findById(id);
		if (!pOptional.isPresent()) {
			LOGGER.error("Erro ao remover o lançamento id {}, o mesmo não consta na base de dados.", id);
			response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		pointReleasesService.delete(id);
		return ResponseEntity.ok(response);
	}

	/**
	 * Converte um DTO para PointReleasesEntity
	 * 
	 * @param pointReleasesDto
	 * @param bindingResult
	 * @return
	 * @throws ParseException
	 */
	private PointReleasesEntity dtoToPointReleasesEntity(PointReleasesDto pointReleasesDto, BindingResult bindingResult)
			throws ParseException {
		PointReleasesEntity pointReleasesEntity = new PointReleasesEntity();
		if (pointReleasesDto.getId().isPresent()) {
			Optional<PointReleasesEntity> optional = pointReleasesService.findById(pointReleasesDto.getId().get());
			if (optional.isPresent()) {
				pointReleasesEntity = optional.get();
				if(pointReleasesDto.getEmployeeId() != pointReleasesEntity.getEmployeesEntity().getId()) {
					Optional<EmployeesEntity> employeesEntity = employeesService.findById(pointReleasesDto.getEmployeeId());
					if(employeesEntity.isPresent()) {
						pointReleasesEntity.setEmployeesEntity(employeesEntity.get());
					}
				}
			} else {
				bindingResult.addError(new ObjectError("Lançamento", "Lançamento não encontrado"));
			}
		} else {
			pointReleasesEntity.setEmployeesEntity(new EmployeesEntity());
			pointReleasesEntity.getEmployeesEntity().setId(pointReleasesDto.getEmployeeId());
		}

		pointReleasesEntity.setDescription(pointReleasesDto.getDescription());
		pointReleasesEntity.setLocation(pointReleasesDto.getLocation());
		pointReleasesEntity.setReleaseDate(dateFormat.parse(pointReleasesDto.getDate()));

		if (EnumUtils.isValidEnum(TypeReleaseEnum.class, pointReleasesDto.getTypeRelease())) {
			pointReleasesEntity.setTypeReleaseEnum(TypeReleaseEnum.valueOf(pointReleasesDto.getTypeRelease()));
		} else {
			bindingResult.addError(new ObjectError("Tipo lançamento", "Tipo de lançamento inválido."));
		}
		return pointReleasesEntity;
	}

	/**
	 * Converte um PointReleasesEntity para DTO.
	 * 
	 * @param pointReleaseEntity
	 * @return
	 */
	private PointReleasesDto entityToPointReleasesDto(PointReleasesEntity pointReleaseEntity) {
		PointReleasesDto pointReleasesDto = new PointReleasesDto();
		pointReleasesDto.setId(Optional.of(pointReleaseEntity.getId()));
		pointReleasesDto.setDate(dateFormat.format(pointReleaseEntity.getReleaseDate()));
		pointReleasesDto.setLocation(pointReleaseEntity.getLocation());
		pointReleasesDto.setDescription(pointReleaseEntity.getDescription());
		pointReleasesDto.setTypeRelease(pointReleaseEntity.getTypeReleaseEnum().toString());
		pointReleasesDto.setEmployeeId(pointReleaseEntity.getEmployeesEntity().getId());
		return pointReleasesDto;
	}

	/**
	 * Verifica se o funcionário existe na base de dados.
	 * 
	 * @param pointReleasesDto
	 * @param bindingResult
	 */
	private void validateEmployee(PointReleasesDto pointReleasesDto, BindingResult bindingResult) {
		if (pointReleasesDto.getEmployeeId() == null) {
			bindingResult.addError(new ObjectError("Funcionário", "Funcionário não informado."));
		}

		Optional<EmployeesEntity> optional = employeesService.findById(pointReleasesDto.getEmployeeId());
		if (!optional.isPresent()) {
			bindingResult.addError(new ObjectError("Funcionário",
					"Funcionário não encontrado para o Id " + pointReleasesDto.getEmployeeId()));
		}

	}
}