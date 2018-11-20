package br.com.prevodonto.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.dto.ClienteDTO;
import javassist.NotFoundException;

/**
 * @author yggor
 *
 */
public interface ClienteService {

	/**
	 * @param cliente
	 * @return
	 */
	Cliente salvar(Cliente cliente);

	/**
	 * @param id
	 * @throws NotFoundException
	 */
	void deletar(Long id) throws NotFoundException;

	Cliente filtrarPorCpf(String cpf) throws NotFoundException;

	public Cliente find(Long id) throws NotFoundException;

	public Cliente atualizar(Cliente cliente) throws NotFoundException;

	public List<Cliente> findAll();
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	public Cliente fromDTO(ClienteDTO clienteDto);

	public Cliente findByNome(String nome) throws NotFoundException;
	

}
