package br.com.prevodonto.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prevodonto.domain.Atendimento;
import br.com.prevodonto.service.AtendimentoService;

@RestController
@RequestMapping(value = "/atendimentos")
public class AtendimentoResource {
	
	private AtendimentoService service;
	@Autowired
	public AtendimentoResource(AtendimentoService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Atendimento> findById(@PathVariable Long id){
		return ResponseEntity.ok(this.service.find(id));
	}

}
