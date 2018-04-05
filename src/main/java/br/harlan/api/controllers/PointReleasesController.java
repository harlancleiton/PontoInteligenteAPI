package br.harlan.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.dtos.PointReleasesDto;
import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.entities.PointReleasesEntity;
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
			@PathVariable("employeeId") Long employeeId, @RequestParam(value = "pag", defaultValue = "0") int page,
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

		//pointReleaseEn
		return ResponseEntity.ok(response);
	}

	private PointReleasesEntity dtoToPointReleasesEntity(PointReleasesDto pointReleasesDto, BindingResult bindingResult) {
		// TODO Auto-generated method stub
		return null;
	}

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
}