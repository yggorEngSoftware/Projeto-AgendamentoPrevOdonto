package br.com.prevodonto.resources;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prevodonto.domain.Dentista;
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
	
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Dentista> salvar(@RequestBody @Valid Dentista cliente) {
		return ResponseEntity.ok(this.service.salvar(cliente));
	}


	@GetMapping
	@CrossOrigin
	public ResponseEntity<Dentista> findByCpf(@RequestParam(name = "cpf") String cpf) throws NotFoundException {
		return ResponseEntity.ok(this.service.filtrarPorCpf(cpf));
	}
	
	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Dentista> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.find(id));
	}

}
