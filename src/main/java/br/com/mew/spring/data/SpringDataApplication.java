package br.com.mew.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.mew.spring.data.service.CrudServicoService;
import br.com.mew.spring.data.service.CrudProfissionalService;
import br.com.mew.spring.data.service.CrudHorarioService;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudServicoService servicoService;
	private final CrudProfissionalService profissionalService;
	private final CrudHorarioService horarioService;


	public SpringDataApplication(CrudServicoService servicoService,
                                 CrudProfissionalService profissionalService,
                                 CrudHorarioService horarioService) {
		
		this.servicoService = servicoService;
		this.profissionalService = profissionalService;
		this.horarioService = horarioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Profissional");
			System.out.println("2 - Serviço");
			System.out.println("3 - Horario");


			
			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					profissionalService.inicial(scanner);
					break;
				case 2:
					servicoService.inicial(scanner);
					break;				
				case 3:
					horarioService.inicial(scanner);
					break;
				default:
					System.out.println("Finalizando...");
					system = false;
					break;
			}
		}
	}
}
