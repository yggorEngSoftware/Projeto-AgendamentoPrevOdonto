package br.com.prevodonto.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.prevodonto.domain.Atendimento;
import br.com.prevodonto.dto.AtendimentoNewDTO;
import javassist.NotFoundException;

/**
 * @author yggor
 *
 */
public interface AtendimentoService {

	/**
	 * @param dentista
	 * @return
	 */

	public Atendimento find(Long id);

	public Atendimento salvar(Atendimento atendimento);

	public Atendimento fromDTO(AtendimentoNewDTO atendimentoNewDto);

	public void deletar(Long id) throws NotFoundException;

	 public List<Atendimento> findAll();

	public Page<Atendimento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

	public Atendimento atualizar(Atendimento atendimento) throws NotFoundException;

	public List<Atendimento> findAllByCpfDentista(String cpf);


}