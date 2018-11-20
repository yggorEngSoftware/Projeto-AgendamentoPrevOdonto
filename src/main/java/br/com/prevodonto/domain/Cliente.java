package br.com.prevodonto.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@NotBlank(message = "nome nao pode vir nullo")
	private String nome;
//	@CPF(message = "cpf invalido")
	private String cpf;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataInscricao = LocalDate.now();
	

	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade= CascadeType.REFRESH)
	private List<Atendimento> atendimento = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(Long id, String nome, String cpf, LocalDate dataInscricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataInscricao = dataInscricao;
	}
	
	public List<Atendimento> getAtendimento() {
		return atendimento;
	}
	
	public void setAtendimento(List<Atendimento> atendimento) {
		this.atendimento = atendimento;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(LocalDate dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

}
