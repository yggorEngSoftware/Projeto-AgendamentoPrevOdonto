package br.com.prevodonto.service;

import br.com.prevodonto.domain.Atendimento;

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

}