package br.com.prevodonto.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prevodonto.domain.Atendimento;
import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.repository.AtendimentoRepository;
import br.com.prevodonto.service.AtendimentoService;
import br.com.prevodonto.service.exception.ObjectNotFoundException;
@Service
public class AtendimentoServiceImpl implements AtendimentoService {

	private AtendimentoRepository repository;

	@Autowired
	public AtendimentoServiceImpl(AtendimentoRepository repository) {
		this.repository = repository;
	}

	@Override
	public Atendimento find(Long id) {
		Optional<Atendimento> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado: Id: " +id + Atendimento.class.getName()));

	}

}
