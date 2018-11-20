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

import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.dto.ClienteDTO;
import br.com.prevodonto.service.ClienteService;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	private ClienteService service;

	@Autowired
	public ClienteResource(ClienteService service) {
		this.service = service;
	}

	@GetMapping
	@CrossOrigin
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = this.service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/page")
	@CrossOrigin
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/cpf")
	@CrossOrigin
	public ResponseEntity<Cliente> findByCpf(@RequestParam(name = "cpf") String cpf) throws NotFoundException {
		return ResponseEntity.ok(this.service.filtrarPorCpf(cpf));
	}

	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Cliente> findById(@PathVariable Long id) throws NotFoundException {
		return ResponseEntity.ok(this.service.find(id));
	}
	@GetMapping(value = "/nome")
	@CrossOrigin
	public ResponseEntity<Cliente> findByNome(@RequestParam(name = "nome") String nome) throws NotFoundException {
		return ResponseEntity.ok(this.service.findByNome(nome));
	}
	
	

	@PostMapping
	@CrossOrigin
	public ResponseEntity<Cliente> salvar(@RequestBody @Valid ClienteDTO clienteDto) {
		Cliente cliente = this.service.fromDTO(clienteDto);
		cliente = this.service.salvar(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Void> atualizar(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Long id)
			throws NotFoundException {
		Cliente cliente = service.fromDTO(clienteDto);
		cliente.setId(id);
		cliente = this.service.atualizar(cliente);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Void> deletar(@PathVariable Long id) throws NotFoundException {
		this.service.deletar(id);
		return ResponseEntity.noContent().build();
	}
//	@DeleteMapping
//	@CrossOrigin
//	public ResponseEntity<Void> deletar(@RequestParam Long id)throws NotFoundException {
////		return ResponseEntity.ok(this.service.deletar(id));
//		this.service.deletar(id);
//		return null;
//	}

}
