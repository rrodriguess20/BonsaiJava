package application;

import java.util.Scanner;

import model.controller.CompraController;
import model.controller.FornecedorController;
import model.controller.FuncionarioController;
import model.controller.VendaController;


/* Classe que implementa todos 
 * os menus e opções de escolha 
 * da aplicação
 */

public class Menu {

	// Importa todos os controllers necessários
	private final FornecedorController fornecedorController;
	private final FuncionarioController funcionarioController;
	private final CompraController compraController;
	private final VendaController vendaController;
	private final Scanner sc = new Scanner(System.in);

	// Construtor do Menu que recebe os controllers como parâmetros

	public Menu(FornecedorController fornecedorController,
				FuncionarioController funcionarioController,
				CompraController compraController,
				VendaController vendaController) {
		this.fornecedorController = fornecedorController;
		this.funcionarioController = funcionarioController;
		this.compraController = compraController;
		this.vendaController = vendaController;
	}

	private static final void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

	public void start() {
		boolean running = true; // Se rodando o menu principal
		while (running) {
			clear(); // Limpa a tela
			System.out.println("================ Supermercado Bonsai  ================");
			System.out.println("1 - Gestão de Fornecedores");
			System.out.println("2 - Gestão de Funcionários");
			System.out.println("3 - Gestão de Compras");
			System.out.println("4 - Gestão de Vendas");
			System.out.println("0 - Sair");
			int option = readInt("Escolha uma opção:");
			switch (option) {
				case 1 -> fornecedoresMenu();
				case 2 -> funcionariosMenu();
				case 3 -> comprasMenu();
				case 4 -> vendasMenu();
				case 0 -> running = false;
				default -> {
					System.out.println("Opção inválida!");
					pause();
				}
			}
		}
		System.out.println("Encerrando aplicação. Até logo!");
	}

	private void fornecedoresMenu() {
		boolean back = false;
		while (!back) {
			clear();
			System.out.println("---------- Fornecedores ----------");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Listar todos");
			System.out.println("4 - Buscar por ID");
			System.out.println("5 - Buscar por CNPJ");
			System.out.println("6 - Buscar por nome");
			System.out.println("7 - Deletar");
			System.out.println("0 - Voltar");
			int option = readInt("Selecione uma opção:");
			switch (option) {
				case 1 -> fornecedorController.cadastrarFornecedor();
				case 2 -> fornecedorController.atualizarFornecedor();
				case 3 -> fornecedorController.listarFornecedores();
				case 4 -> fornecedorController.buscarFornecedorPorId();
				case 5 -> fornecedorController.buscarFornecedorPorCnpj();
				case 6 -> fornecedorController.buscarFornecedoresPorNome();
				case 7 -> fornecedorController.deletarFornecedor();
				case 0 -> back = true;
				default -> System.out.println("Opção inválida!");
			}
			if (!back) {
				pause();
			}
		}
	}

	private void funcionariosMenu() {
		boolean back = false;
		while (!back) {
			clear();
			System.out.println("---------- Funcionários ----------");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Listar todos");
			System.out.println("4 - Buscar por ID");
			System.out.println("5 - Deletar");
			System.out.println("0 - Voltar");
			int option = readInt("Selecione uma opção:");
			switch (option) {
				case 1 -> funcionarioController.cadastrarNovoFuncionario();
				case 2 -> funcionarioController.atualizarFuncionario();
				case 3 -> funcionarioController.listarFuncionarios();
				case 4 -> funcionarioController.buscarFuncionarioPorId();
				case 5 -> funcionarioController.deletarFuncionario();
				case 0 -> back = true;
				default -> System.out.println("Opção inválida!");
			}
			if (!back) {
				pause();
			}
		}
	}

	private void comprasMenu() {
		boolean back = false;
		while (!back) {
			clear();
			System.out.println("---------- Compras ----------");
			System.out.println("1 - Registrar compra");
			System.out.println("2 - Atualizar compra");
			System.out.println("3 - Listar todas");
			System.out.println("4 - Buscar por ID");
			System.out.println("5 - Deletar");
			System.out.println("0 - Voltar");
			int option = readInt("Selecione uma opção:");
			switch (option) {
				case 1 -> compraController.cadastrarCompra();
				case 2 -> compraController.atualizarCompra();
				case 3 -> compraController.listarCompra();
				case 4 -> compraController.buscarCompraPorId();
				case 5 -> compraController.deletarCompra();
				case 0 -> back = true;
				default -> System.out.println("Opção inválida!");
			}
			if (!back) {
				pause();
			}
		}
	}

	private void vendasMenu() {
		boolean back = false;
		while (!back) {
			clear();
			System.out.println("---------- Vendas ----------");
			System.out.println("1 - Registrar venda");
			System.out.println("2 - Atualizar venda");
			System.out.println("3 - Listar todas");
			System.out.println("4 - Buscar por ID");
			System.out.println("5 - Deletar venda");
			System.out.println("0 - Voltar");
			int option = readInt("Selecione uma opção:");
			switch (option) {
				case 1 -> vendaController.registrarVenda();
				case 2 -> vendaController.atualizarVenda();
				case 3 -> vendaController.listarVendas();
				case 4 -> vendaController.buscarVendaPorId();
				case 5 -> vendaController.deletarVenda();
				case 0 -> back = true;
				default -> System.out.println("Opção inválida!");
			}
			if (!back) {
				pause();
			}
		}
	}

	private int readInt(String message) {
		System.out.println(message);
		try {
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida. Tente novamente.");
			return -1;
		}
	}

	private void pause() {
		System.out.println("Pressione Enter para continuar...");
		sc.nextLine();
	}
}
