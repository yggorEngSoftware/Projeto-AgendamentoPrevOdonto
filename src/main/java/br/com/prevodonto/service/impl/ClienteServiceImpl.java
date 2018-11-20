package br.com.prevodonto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.dto.ClienteDTO;
import br.com.prevodonto.repository.ClienteRepository;
import br.com.prevodonto.service.ClienteService;
import br.com.prevodonto.service.exception.ObjectNotFoundException;
import javassist.NotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepository repository;

	@Autowired
	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public Cliente salvar(Cliente cliente) {
		cliente.setId(null);
		return this.repository.save(cliente);
	}

	@Override
	public void deletar(Long id) throws NotFoundException {
		Cliente clienteEncontrado = this.repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		this.repository.delete(clienteEncontrado);
	}

	@Override
	public Cliente filtrarPorCpf(String cpf) throws NotFoundException {
		return this.repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Cpf não encontrado"));
	}
	@Override
	public Cliente find(Long id) {
//		Optional<Cliente> obj = repository.findById(id);
		return this.repository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado: Id: " + id + Cliente.class.getName()));
	}

	@Override
	public Cliente atualizar(Cliente cliente) throws NotFoundException {
		Cliente clienteEncontrado = find(cliente.getId());
//		clienteEncontrado.setNome(cliente.getNome());
//		clienteEncontrado.setCpf(cliente.getCpf());
		updateData(clienteEncontrado, cliente);
//				repository.findById(cliente.getId())
//				.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		return this.repository.save(clienteEncontrado);
	}

	@Override
	public List<Cliente> findAll() {
		return this.repository.findAll();
	}
	@Override
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}
	@Override
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getCpf(),
				clienteDTO.getDataInscricao());
	}

	private void updateData(Cliente clienteEncontrado, Cliente cliente) {
		clienteEncontrado.setNome(cliente.getNome());
		clienteEncontrado.setCpf(cliente.getCpf());
//		clienteEncontrado.setDataInscricao(cliente.getDataInscricao());
//		clienteEncontrado.setDentista(cliente.getDentista());
	}

	@Override
	public Cliente findByNome(String nome) throws NotFoundException {
		return this.repository.findByNome(nome).orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado: Nome: " + nome  + Cliente.class.getName()));
	}
}
