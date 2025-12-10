package model.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import model.entities.Funcionario;
import model.entities.Venda;
import model.service.FuncionarioService;
import model.service.VendaService;

public class VendaController {

	private final VendaService vendaService;
	private final FuncionarioService funcionarioService;
	private final Scanner sc = new Scanner(System.in);

	public VendaController(VendaService vendaService, FuncionarioService funcionarioService) {
		this.vendaService = vendaService;
		this.funcionarioService = funcionarioService;
	}

	public void registrarVenda() {
		Funcionario funcionario = solicitarFuncionario();
		if (funcionario == null) {
			return;
		}

		Double total = solicitarDouble("Digite o valor total da venda:");
		if (total == null) {
			System.out.println("Valor inválido. Operação cancelada.");
			return;
		}

		Venda venda = new Venda();
		venda.setFuncionario(funcionario);
		venda.setDataVenda(LocalDateTime.now());
		venda.setTotal(total);

		vendaService.adicionarVenda(venda);
		System.out.println("Venda cadastrada com sucesso!");
	}

	public void atualizarVenda() {
		int id = solicitarInteiro("Informe o ID da venda que deseja atualizar:");
		if (id <= 0) {
			System.out.println("ID inválido. Operação cancelada.");
			return;
		}

		Venda venda = vendaService.buscarVendaPorId(id);
		if (venda == null) {
			System.out.println("Venda não encontrada.");
			return;
		}

		System.out.println("Deseja alterar o funcionário responsável? (s/N)");
		String alterarFuncionario = sc.nextLine().trim();
		if (alterarFuncionario.equalsIgnoreCase("s")) {
			Funcionario funcionario = solicitarFuncionario();
			if (funcionario != null) {
				venda.setFuncionario(funcionario);
			}
		}

		System.out.println("Informe o novo total (ou pressione Enter para manter):");
		String totalStr = sc.nextLine();
		if (!totalStr.isBlank()) {
			try {
				venda.setTotal(Double.parseDouble(totalStr));
			} catch (NumberFormatException e) {
				System.out.println("Valor inválido. O total anterior foi mantido.");
			}
		}

		vendaService.editarVenda(venda);
		System.out.println("Venda atualizada com sucesso!");
	}

	public void listarVendas() {
		List<Venda> vendas = vendaService.listarTodasVendas();
		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada.");
			return;
		}

		for (Venda venda : vendas) {
			imprimirVenda(venda);
		}
	}

	public void deletarVenda() {
		int id = solicitarInteiro("Informe o ID da venda que deseja deletar:");
		if (id <= 0) {
			System.out.println("ID inválido. Operação cancelada.");
			return;
		}

		Venda venda = vendaService.buscarVendaPorId(id);
		if (venda == null) {
			System.out.println("Venda não encontrada.");
			return;
		}

		vendaService.deletarVenda(venda);
		System.out.println("Venda deletada com sucesso!");
	}

	public void buscarVendaPorId() {
		int id = solicitarInteiro("Digite o ID da venda que deseja buscar:");
		if (id <= 0) {
			System.out.println("ID inválido.");
			return;
		}

		Venda venda = vendaService.buscarVendaPorId(id);
		if (venda == null) {
			System.out.println("Venda não encontrada.");
			return;
		}

		imprimirVenda(venda);
	}

	private Funcionario solicitarFuncionario() {
		int funcionarioId = solicitarInteiro("Digite o ID do funcionário responsável:");
		if (funcionarioId <= 0) {
			System.out.println("ID inválido.");
			return null;
		}

		Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(funcionarioId);
		if (funcionario == null) {
			System.out.println("Funcionário não encontrado.");
		}
		return funcionario;
	}

	private int solicitarInteiro(String mensagem) {
		System.out.println(mensagem);
		try {
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private Double solicitarDouble(String mensagem) {
		System.out.println(mensagem);
		try {
			return Double.parseDouble(sc.nextLine());
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private void imprimirVenda(Venda venda) {
		String funcionarioNome = venda.getFuncionario() != null && venda.getFuncionario().getNome() != null
				? venda.getFuncionario().getNome()
				: "N/D";

		System.out.printf("Venda #%d | Funcionário: %s | Data: %s | Total: %.2f%n",
				venda.getId(),
				funcionarioNome,
				venda.getDataVenda(),
				venda.getTotal());
	}
}
