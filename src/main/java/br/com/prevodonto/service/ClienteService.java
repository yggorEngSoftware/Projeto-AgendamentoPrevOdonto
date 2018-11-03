package br.com.prevodonto.service;

import br.com.prevodonto.domain.Cliente;
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
	void deletar(Integer id) throws NotFoundException;
	
	Cliente filtrarPorCpf(String cpf) throws NotFoundException;
	
	public Cliente find(Integer id) ;
}
