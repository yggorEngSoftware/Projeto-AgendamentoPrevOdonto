package br.com.prevodonto.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prevodonto.domain.Cliente;

@RestController
@RequestMapping(value = "/dentistas")
public class DentistaResource {
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Cliente> listar() {
		
		return null;
	}

}
