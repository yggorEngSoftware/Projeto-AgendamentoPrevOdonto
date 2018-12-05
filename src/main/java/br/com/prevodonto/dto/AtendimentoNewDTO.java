package br.com.prevodonto.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AtendimentoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(pattern = "d/M/yyyy H:mm")
	private LocalDateTime dataAtendimento;
	private String servico;
	private Integer estado;
	private Long clienteId;
	private Long dentistaId;

	private String nomeCliente;
	private String cpfCliente;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInscricao = LocalDate.now();

	private String nomeDentista;
	private String cpfDentista;
	private String especialidade;

	public AtendimentoNewDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public LocalDate getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(LocalDate dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Long getDentistaId() {
		return dentistaId;
	}

	public void setDentistaId(Long dentistaId) {
		this.dentistaId = dentistaId;
	}

	public String getNomeDentista() {
		return nomeDentista;
	}

	public void setNomeDentista(String nomeDentista) {
		this.nomeDentista = nomeDentista;
	}

	public String getCpfDentista() {
		return cpfDentista;
	}

	public void setCpfDentista(String cpfDentista) {
		this.cpfDentista = cpfDentista;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

}
