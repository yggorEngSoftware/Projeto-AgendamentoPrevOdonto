package br.com.prevodonto.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prevodonto.domain.Cliente;
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

	@PostMapping
	@CrossOrigin
	public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(this.service.salvar(cliente));
	}

	@GetMapping
	@CrossOrigin
	public ResponseEntity<Cliente> findByCpf(@RequestParam(name = "cpf") String cpf) throws NotFoundException {
		return ResponseEntity.ok(this.service.filtrarPorCpf(cpf));
	}
	
	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.find(id));
	}
}
