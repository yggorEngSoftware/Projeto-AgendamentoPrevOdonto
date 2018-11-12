package br.com.prevodonto.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.repository.DentistaRepository;
import br.com.prevodonto.service.DentistaService;
//import br.com.prevodonto.service.DentistaService;
import br.com.prevodonto.service.exception.ObjectNotFoundException;
import javassist.NotFoundException;

@Service
public class DentistaServiceImpl implements DentistaService {

	private DentistaRepository repository;

	@Autowired
	public DentistaServiceImpl(DentistaRepository repository) {
		this.repository = repository;
	}

	public Dentista salvar(Dentista dentista) {
		return this.repository.save(dentista);
	}

	@Override
	public void deletar(Long id) throws NotFoundException {
		Dentista dentistaEncontrado = this.repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Dentista não encontrado"));
		this.repository.delete(dentistaEncontrado);
	}

	@Override
	public Dentista filtrarPorCpf(String cpf) throws NotFoundException {
		return this.repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Cpf não encontrado"));
	}

	@Override
	public Dentista find(Long id) {
		Optional<Dentista> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado: Id: " + id + Dentista.class.getName()));
	}

}
