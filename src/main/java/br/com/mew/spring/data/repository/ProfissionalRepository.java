package br.com.mew.spring.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import br.com.mew.spring.data.domain.Profissional;
import br.com.mew.spring.data.domain.ProfissionalProjecao;

@Repository
public interface ProfissionalRepository extends PagingAndSortingRepository<Profissional, Integer>,
		JpaSpecificationExecutor<Profissional>{
	List<Profissional> findByNome(String nome);
	
	@Query("SELECT f FROM Profissional f WHERE f.nome = :nome AND f.senha >= :senha AND f.dataInicio = :data")
	List<Profissional> findNomeSenhaMaiorDataInicio(String nome, String senha, LocalDate data);
	
	@Query(value = "SELECT * FROM profissionais f WHERE f.data_inicio >= :data",
			nativeQuery = true)
	List<Profissional> findaDataInicioMaior(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.senha FROM profissional f",
			nativeQuery = true)
	List<ProfissionalProjecao> findProfissionalSenha();
}
