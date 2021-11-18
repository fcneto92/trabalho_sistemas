package br.com.mew.spring.data.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.mew.spring.data.domain.Profissional;

public class SpecificationProfissional {

	public static Specification<Profissional> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		
	}
	
	public static Specification<Profissional> login(String login) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("login"), login);
		
	}
	
	public static Specification<Profissional> senha(String senha) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.greaterThan(root.get("senha"), senha);
		
	}
	
	public static Specification<Profissional> dataInicio(LocalDate dataInicio) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.greaterThan(root.get("dataInicio"), dataInicio);
		
	}
	
	
}
