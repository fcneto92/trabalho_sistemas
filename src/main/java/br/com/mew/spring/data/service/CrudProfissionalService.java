package br.com.mew.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.mew.spring.data.domain.Servico;
import br.com.mew.spring.data.domain.Profissional;
import br.com.mew.spring.data.domain.Horario;
import br.com.mew.spring.data.repository.ServicoRepository;
import br.com.mew.spring.data.repository.ProfissionalRepository;
import br.com.mew.spring.data.repository.HorarioRepository;

@Service
public class CrudProfissionalService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final ServicoRepository servicoRepository;
	private final ProfissionalRepository profissionalRepository;
	private final HorarioRepository horarioRepository;
	
	
	public CrudProfissionalService(ProfissionalRepository profissionalRepository, ServicoRepository servicoRepository, HorarioRepository horarioRepository) {
		this.servicoRepository = servicoRepository;
		this.profissionalRepository = profissionalRepository;
		this.horarioRepository = horarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de profissional deseja executar:");
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
				visualizar(scanner);
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
		System.out.println("Digite o nome:");
        String nome = scanner.next();

        System.out.println("Digite o login:");
        String login = scanner.next();

        System.out.println("Digite a senha:");
        String senha = scanner.next();

        System.out.println("Digite a data de início:");
        String dataInicio = scanner.next();

		List<Servico> servicos = servico(scanner);

        List<Horario> horarios = horario(scanner);

        Profissional profissional = new Profissional();
        profissional.setNome(nome);
        profissional.setLogin(login);
        profissional.setSenha(senha);
        profissional.setDataInicio(LocalDate.parse(dataInicio, formatter));
        profissional.setServico(servicos);
        profissional.setHorario(horarios);

        profissionalRepository.save(profissional);
        System.out.println("Salvo");
	}
	
	private List<Horario> horario(Scanner scanner) {
        Boolean isTrue = true;
        List<Horario> horarios = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o horarioId (Para sair digite 0)");
            Integer horarioId = scanner.nextInt();

            if(horarioId != 0) {
                Optional<Horario> horario = horarioRepository.findById(horarioId);
                horarios.add(horario.get());
            } else {
                isTrue = false;
            }
        }

        return horarios;
    }

	private List<Servico> servico(Scanner scanner) {
		Boolean isTrue = true;
		List<Servico> servicos = new ArrayList<>();

		while (isTrue) {
			System.out.println("Digite o servicoId (Para sair digite 0)");
			Integer servicoId = scanner.nextInt();

			if(servicoId != 0) {
				Optional<Servico> servico = servicoRepository.findById(servicoId);
				servicos.add(servico.get());
			} else {
				isTrue = false;
			}
		}

		return servicos;
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id:");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome:");
        String nome = scanner.next();

        System.out.println("Digite o login:");
        String login = scanner.next();

        System.out.println("Digite a senha:");
        String senha = scanner.next();

        System.out.println("Digite a data de início:");
        String dataInicio = scanner.next();


        Profissional profissional = new Profissional();
        profissional.setId(id);
        profissional.setNome(nome);
        profissional.setLogin(login);
        profissional.setSenha(senha);
        profissional.setDataInicio(LocalDate.parse(dataInicio, formatter));

        profissionalRepository.save(profissional);
        System.out.println("Alterado");
	}
	
	private void visualizar(Scanner scanner) {
		System.out.println("Qual página você deseja visualizar:");
		Integer page = scanner.nextInt();
		
		org.springframework.data.domain.Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Profissional> profissionais = profissionalRepository.findAll(pageable);
		
		System.out.println(profissionais);
		System.out.println("Página atual: " + profissionais.getNumber());
		System.out.println("Total de elementos: " + profissionais.getTotalElements());
		profissionais.forEach(funcionario -> System.out.println(profissionais));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		profissionalRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
}
