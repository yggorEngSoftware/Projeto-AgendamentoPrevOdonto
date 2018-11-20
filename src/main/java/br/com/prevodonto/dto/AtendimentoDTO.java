package br.com.prevodonto.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prevodonto.domain.Atendimento;

public class AtendimentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAtendimento;
	private String servico;
	private Integer estado;
	private Long clienteId;
	private Long dentistaId;

	private String nomeCliente;

	private String nomeDentista;

	public AtendimentoDTO() {
	}

	public AtendimentoDTO(Atendimento atendimento) {
		this.id = atendimento.getId();
		this.dataAtendimento = atendimento.getDataAtendimento();
		this.servico = atendimento.getServico();
		this.estado = atendimento.getEstado().getCod();
		this.clienteId = atendimento.getCliente().getId();
		this.dentistaId = atendimento.getDentista().getId();
		this.nomeCliente = atendimento.getCliente().getNome();
		this.nomeDentista = atendimento.getDentista().getNome();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDateTime dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getDentistaId() {
		return dentistaId;
	}

	public void setDentistaId(Long dentistaId) {
		this.dentistaId = dentistaId;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeDentista() {
		return nomeDentista;
	}

	public void setNomeDentista(String nomeDentista) {
		this.nomeDentista = nomeDentista;
	}

}
