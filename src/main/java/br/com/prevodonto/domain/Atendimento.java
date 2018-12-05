package br.com.prevodonto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prevodonto.domain.enums.EstadoAtendimento;

@Entity
public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern="d/M/yyyy H:mm")
	private LocalDateTime dataAtendimento;
	private String servico;
	private Integer estado;

	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "dentista_id")
	private Dentista dentista;

	public Atendimento(Long id, LocalDateTime dataAtendimento, String servico, Cliente cliente, Dentista dentista,
			EstadoAtendimento estado) {
		this.id = id;
		this.dataAtendimento = dataAtendimento;
		this.servico = servico;
		this.cliente = cliente;
		this.dentista = dentista;
		this.estado = estado.getCod();
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

	public EstadoAtendimento getEstado() {
		return EstadoAtendimento.toEnum(estado);
	}

	public void setEstado(EstadoAtendimento estado) {
		this.estado = estado.getCod();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public Atendimento() {

	}

}
