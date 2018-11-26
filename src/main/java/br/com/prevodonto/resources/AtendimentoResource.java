package br.com.prevodonto.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.prevodonto.domain.Atendimento;
import br.com.prevodonto.dto.AtendimentoDTO;
import br.com.prevodonto.dto.AtendimentoNewDTO;
import br.com.prevodonto.service.AtendimentoService;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/atendimentos")
public class AtendimentoResource {

	private AtendimentoService service;

	@Autowired
	public AtendimentoResource(AtendimentoService service) {
		this.service = service;
	}

	@GetMapping
	@CrossOrigin
	public ResponseEntity<List<AtendimentoDTO>> findAll() {
		List<Atendimento> list = this.service.findAll();
		List<AtendimentoDTO> listDto = list.stream().map(obj -> new AtendimentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/cpf")
	@CrossOrigin
	public ResponseEntity<List<AtendimentoDTO>> findAllByCpfDentista(@RequestParam(name = "cpf") String cpf) {
		List<Atendimento> list = this.service.findAllByCpfDentista(cpf);
		List<AtendimentoDTO> listDto = list.stream().map(obj -> new AtendimentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Atendimento> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.find(id));
	}

	@GetMapping(value = "/page")
	@CrossOrigin
	public ResponseEntity<Page<AtendimentoDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "dataAtendimento") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Atendimento> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<AtendimentoDTO> listDto = list.map(obj -> new AtendimentoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	@CrossOrigin
	public ResponseEntity<Atendimento> salvar(@Valid @RequestBody AtendimentoNewDTO atendimentoNewDto) {
		Atendimento atendimento = this.service.fromDTO(atendimentoNewDto);
		atendimento = this.service.salvar(atendimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atendimento.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Void> atualizar(@Valid @RequestBody AtendimentoNewDTO atendimentoNewDto,
			@PathVariable Long id) throws NotFoundException {
		Atendimento atendimento = service.fromDTO(atendimentoNewDto);
		atendimento.setId(id);
		atendimento = this.service.atualizar(atendimento);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Void> deletar(@PathVariable Long id) throws NotFoundException {
		this.service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
