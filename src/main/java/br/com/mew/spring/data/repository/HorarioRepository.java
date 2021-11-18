package br.com.mew.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mew.spring.data.domain.Horario;

@Repository
public interface HorarioRepository extends CrudRepository<Horario, Integer> {

}
