package br.com.mew.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mew.spring.data.domain.Horario;
import br.com.mew.spring.data.repository.HorarioRepository;

@Service
public class CrudHorarioService {

	private Boolean system = true;
	private final HorarioRepository horarioRepository;
	
	public CrudHorarioService(HorarioRepository horarioRepository) {
		this.horarioRepository = horarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Horário deseja executar:");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
		
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Digite o horário:");
        String nome = scanner.next();

        Horario horario = new Horario();
        horario.setDescricao(nome);

        horarioRepository.save(horario);
        System.out.println("Salvo!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id do horário:");
        Integer id = scanner.nextInt();

        System.out.println("Digite o horário:");
        String nome = scanner.next();

        Horario horario = new Horario();
        horario.setId(id);
        horario.setDescricao(nome);

        horarioRepository.save(horario);
        System.out.println("Alterado!");
	}
	
	private void visualizar() {
		Iterable<Horario> horarios = horarioRepository.findAll();
		horarios.forEach(horario -> System.out.println(horario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id:");
		int id = scanner.nextInt();
		horarioRepository.deleteById(id);
		System.out.println("Deletado!");
	}
	
}
