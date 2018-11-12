package br.com.prevodonto.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "nome nao pode vir nullo")
	private String nome;
//	@CPF(message = "cpf invalido")
	private String cpf;
	private LocalDate dataInscricao = LocalDate.now();
	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "CLIENTE_DENTISTA", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "dentista_id"))
	private List<Dentista> dentista = new ArrayList<>();

	@JsonBackReference
	@OneToMany(mappedBy = "cliente")
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

	public List<Dentista> getDentista() {
		return dentista;
	}

	public void setDentista(List<Dentista> dentista) {
		this.dentista = dentista;
	}

}
