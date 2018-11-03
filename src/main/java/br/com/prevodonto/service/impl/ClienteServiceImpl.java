package br.com.prevodonto.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.repository.ClienteRepository;
import br.com.prevodonto.service.ClienteService;
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
		return this.repository.save(cliente);
	}

	@Override
	public void deletar(Integer id) throws NotFoundException {
		Cliente clienteEncontrado = this.repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		this.repository.delete(clienteEncontrado);
	}

	@Override
	public Cliente filtrarPorCpf(String cpf) throws NotFoundException {
		return this.repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Cpf não encontrado"));
	}

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}

}