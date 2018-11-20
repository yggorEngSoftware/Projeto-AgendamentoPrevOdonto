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

import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.dto.DentistaDTO;
import br.com.prevodonto.service.DentistaService;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/dentistas")
public class DentistaResource {

	private DentistaService service;

	@Autowired
	public DentistaResource(DentistaService service) {
		this.service = service;
	}

	@GetMapping
	@CrossOrigin
	public ResponseEntity<List<DentistaDTO>> findAll() {
		List<Dentista> list = this.service.findAll();
		List<DentistaDTO> listDto = list.stream().map(obj -> new DentistaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/page")
	@CrossOrigin
	public ResponseEntity<Page<DentistaDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Dentista> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<DentistaDTO> listDto = list.map(obj -> new DentistaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/cpf")
	@CrossOrigin
	public ResponseEntity<Dentista> findByCpf(@RequestParam(name = "cpf") String cpf) throws NotFoundException {
		return ResponseEntity.ok(this.service.filtrarPorCpf(cpf));
	}

	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Dentista> findById(@PathVariable Long id) throws NotFoundException {
		return ResponseEntity.ok(this.service.find(id));
	}
	
	@GetMapping(value = "/nome")
	@CrossOrigin
	public ResponseEntity<Dentista> findByNome(@RequestParam(name = "nome") String nome) throws NotFoundException {
		return ResponseEntity.ok(this.service.findByNome(nome));
	}
	

	@PostMapping
	@CrossOrigin
	public ResponseEntity<Dentista> salvar(@RequestBody @Valid DentistaDTO dentistaDto) {
		Dentista dentista = this.service.fromDTO(dentistaDto);
		dentista = this.service.salvar(dentista);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dentista.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Void> atualizar(@RequestBody @Valid DentistaDTO dentistaDto, @PathVariable Long id)
			throws NotFoundException {
		Dentista dentista = this.service.fromDTO(dentistaDto);
		dentista.setId(id);
		dentista = this.service.atualizar(dentista);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Void> deletar(@PathVariable Long id) throws NotFoundException {
		this.service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
