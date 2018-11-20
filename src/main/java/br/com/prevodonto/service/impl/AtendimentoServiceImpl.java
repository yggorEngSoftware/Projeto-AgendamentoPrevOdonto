package br.com.prevodonto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prevodonto.domain.Atendimento;
import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.domain.enums.EstadoAtendimento;
import br.com.prevodonto.dto.AtendimentoNewDTO;
import br.com.prevodonto.repository.AtendimentoRepository;
import br.com.prevodonto.repository.ClienteRepository;
import br.com.prevodonto.repository.DentistaRepository;
import br.com.prevodonto.service.AtendimentoService;
import br.com.prevodonto.service.exception.ObjectNotFoundException;
import javassist.NotFoundException;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

	private AtendimentoRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private DentistaRepository dentistaRespository;
	

	@Autowired
	public AtendimentoServiceImpl(AtendimentoRepository repository) {
		this.repository = repository;
	}

	@Override
	public Atendimento find(Long id) {
		Optional<Atendimento> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado: Id: " + id + Atendimento.class.getName()));

	}
	@Override
	public List<Atendimento> findAll() {
		return this.repository.findAll();
	}
	
	@Override
	public Page<Atendimento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}
	
	

	@Override
	@Transactional
	public Atendimento salvar(Atendimento atendimento) {
		atendimento.setId(null);
//		this.clienteRepository.save(atendimento.getCliente());
//		this.dentistaRespository.save(atendimento.getDentista());
		return this.repository.save(atendimento);
	}
	
	@Override
	public Atendimento atualizar(Atendimento atendimento) throws NotFoundException {
//		Atendimento atendimentoEncontrado = find(atendimento.getId());
				repository.findById(atendimento.getId())
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		return this.repository.save(atendimento);
	}
	@Override
	public void deletar(Long id) throws NotFoundException {
		Atendimento atendimentoEncontrado = this.repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		this.repository.delete(atendimentoEncontrado);
	}

	@Override
	public Atendimento fromDTO(AtendimentoNewDTO atendimentoNewDto) {
	
		Cliente c = clienteRepository.getOne(atendimentoNewDto.getClienteId());
		Dentista d = dentistaRespository.getOne(atendimentoNewDto.getDentistaId());
//		Cliente cli = new Cliente(null, atendimentoNewDto.getNomeCliente(), atendimentoNewDto.getCpfCliente(),
//				atendimentoNewDto.getDataInscricao());
//		Dentista den = new Dentista(null, atendimentoNewDto.getNomeDentista(), atendimentoNewDto.getCpfDentista(),
//				atendimentoNewDto.getEspecialidade());
		
		Atendimento aten = new Atendimento(null, atendimentoNewDto.getDataAtendimento(), atendimentoNewDto.getServico(),
				c, d, EstadoAtendimento.toEnum(atendimentoNewDto.getEstado()));
//		cli.getAtendimento().add(aten);
//		den.getAtendimento().add(aten);
		return aten;
	}
}
