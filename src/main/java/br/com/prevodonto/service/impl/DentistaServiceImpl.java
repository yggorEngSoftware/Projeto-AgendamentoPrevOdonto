package br.com.prevodonto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.dto.DentistaDTO;
import br.com.prevodonto.repository.DentistaRepository;
import br.com.prevodonto.service.DentistaService;
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
		dentista.setId(null);
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

	@Override
	public Dentista atualizar(Dentista dentista) throws NotFoundException {
//		Dentista dentistaEncontrado = this.repository.findById(dentista.getId())
//				.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		Dentista dentistaEncontrado = find(dentista.getId());
		dentistaEncontrado.setNome(dentista.getNome());
		dentistaEncontrado.setCpf(dentista.getCpf());
//		updateData(clienteEncontrado, cliente);
		return this.repository.save(dentistaEncontrado);
	}

	@Override
	public List<Dentista> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Page<Dentista> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}

	@Override
	public Dentista fromDTO(DentistaDTO dentistaDto) {
		return new Dentista(dentistaDto.getId(), dentistaDto.getNome(), dentistaDto.getCpf(), dentistaDto.getEspecialidade());
	}

	@Override
	public Dentista findByNome(String nome) throws NotFoundException {
		return this.repository.findByNome(nome).orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado: Nome: " + nome + Dentista.class.getName()));
	}

}
