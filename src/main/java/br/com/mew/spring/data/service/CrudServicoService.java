package br.com.mew.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mew.spring.data.domain.Servico;
import br.com.mew.spring.data.repository.ServicoRepository;


@Service
public class CrudServicoService {

	private Boolean system = true;
	private final ServicoRepository servicoRepository;

	public CrudServicoService(ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}

	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Servico deseja executar:");
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

		Servico servico = new Servico();
		servico.setDescricao(nome);

		servicoRepository.save(servico);
		System.out.println("Salvo!");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id do serviço:");
		Integer id = scanner.nextInt();

		System.out.println("Digite o serviço:");
		String nome = scanner.next();

		Servico servico = new Servico();
		servico.setId(id);
		servico.setDescricao(nome);

		servicoRepository.save(servico);
		System.out.println("Alterado!");
	}

	private void visualizar() {
		Iterable<Servico> servicos = servicoRepository.findAll();
		servicos.forEach(servico -> System.out.println(servico));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id:");
		int id = scanner.nextInt();
		servicoRepository.deleteById(id);
		System.out.println("Deletado!");
	}

}