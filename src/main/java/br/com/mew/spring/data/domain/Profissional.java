package br.com.mew.spring.data.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "profissional")
public class Profissional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private LocalDate dataInicio;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "profissional_servico", joinColumns = {
			@JoinColumn(name = "fk_profissional") },
			inverseJoinColumns = { @JoinColumn(name = "fk_servico") })
	private List<Servico> servicos;

	@ManyToMany
	@JoinTable(name = "profissional_horario", joinColumns = {
			@JoinColumn(name = "fk_profissional") },
	inverseJoinColumns = { @JoinColumn(name = "fk_horario") })
	private List<Horario> horarios;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}


	public List<Servico> getServico() {
		return servicos;
	}

	public void setServico(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Horario> getHorario() {
		return horarios;
	}

	public void setHorario(List<Horario> horarios) {
		this.horarios = horarios;
	}

	@Override
	public String toString() {
		return "Profissional: " + "id:" + id + "| nome:'" + nome + "| login:" + login + "| senha:" + senha
				+ "| dataInicio:" + dataInicio;
	}
}
