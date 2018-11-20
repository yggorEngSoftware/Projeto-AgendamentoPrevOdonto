package br.com.prevodonto.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.dto.DentistaDTO;
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

	public Dentista atualizar(Dentista dentista) throws NotFoundException;

	public List<Dentista> findAll() ;

	public Page<Dentista> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

	public Dentista fromDTO(DentistaDTO dentistaDto) ;

	public Dentista findByNome(String nome) throws NotFoundException;
	

}
