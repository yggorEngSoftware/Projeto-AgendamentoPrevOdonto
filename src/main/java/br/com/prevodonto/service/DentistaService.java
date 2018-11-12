package br.com.prevodonto.service;

import br.com.prevodonto.domain.Dentista;
import javassist.NotFoundException;

/**
 * @author yggor
 *
 */
public interface DentistaService {

	/**
	 * @param dentista
	 * @return
	 */
	Dentista salvar(Dentista dentista);
	
	/**
	 * @param id
	 * @throws NotFoundException 
	 */
	void deletar(Long id) throws NotFoundException;
	
	Dentista filtrarPorCpf(String cpf) throws NotFoundException;
	
	public Dentista find(Long id);

}
