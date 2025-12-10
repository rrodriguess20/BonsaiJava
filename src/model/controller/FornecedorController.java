package model.controller;

import java.util.List;
import java.util.Scanner;

import model.entities.Fornecedor;
import model.service.FornecedorService;

public class FornecedorController {

    private FornecedorService fornecedorService;
    private final Scanner sc = new Scanner(System.in);

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    public void cadastrarFornecedor() {

        System.out.println("Insira o nome do fornecedor:");
        String nome = sc.nextLine();

        System.out.println("Insira o CNPJ do fornecedor:");
        String cnpj = sc.nextLine();

        System.out.println("Insira o telefone do fornecedor:");
        String telefone = sc.nextLine();

        Fornecedor fornecedor = new Fornecedor(nome, cnpj, telefone);
        fornecedorService.cadastrarFornecedor(fornecedor);

        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    public void atualizarFornecedor() {

        System.out.println("Digite o ID do fornecedor que deseja atualizar:");
        int id = Integer.parseInt(sc.nextLine());

        var fornecedor = fornecedorService.buscarFornecedorPorId(id);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.println("Digite o novo nome (ou pressione Enter para manter):");
        String nome = sc.nextLine();
        if (!nome.isBlank()) {
            fornecedor.setNome(nome);
        }

        System.out.println("Digite o novo CNPJ (ou pressione Enter para manter):");
        String cnpj = sc.nextLine();
        if (!cnpj.isBlank()) {
            fornecedor.setCnpj(cnpj);
        }

        System.out.println("Digite o novo telefone (ou pressione Enter para manter):");
        String telefone = sc.nextLine();
        if (!telefone.isBlank()) {
            fornecedor.setTelefone(telefone);
        }

        fornecedorService.atualizarFornecedor(fornecedor);
        System.out.println("Fornecedor atualizado com sucesso!");
    }

    public void listarFornecedores() {

        List<Fornecedor> fornecedores = fornecedorService.listarTodosFornecedores();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado.");
            return;
        }

        for (Fornecedor f : fornecedores) {
            fornecedorToString(f);
            System.out.println("-------------------");
        }
    }

    public void deletarFornecedor() {

        System.out.println("Digite o ID do fornecedor que deseja deletar:");
        int id = Integer.parseInt(sc.nextLine());

        fornecedorService.deletarFornecedor(id);
        System.out.println("Fornecedor deletado com sucesso!");
    }

    public void buscarFornecedorPorId() {

        System.out.println("Digite o ID do fornecedor que deseja buscar:");
        int id = Integer.parseInt(sc.nextLine());

        var fornecedor = fornecedorService.buscarFornecedorPorId(id);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        fornecedorToString(fornecedor);
    }

    public void buscarFornecedorPorCnpj() {

        System.out.println("Digite o CNPJ do fornecedor:");
        String cnpj = sc.nextLine();

        var fornecedor = fornecedorService.buscarFornecedorPorCnpj(cnpj);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.println(fornecedor);
    }

    public void buscarFornecedoresPorNome() {

        System.out.println("Digite o nome (ou parte do nome) do fornecedor:");
        String nome = sc.nextLine();

        List<Fornecedor> fornecedores = fornecedorService.buscarFornecedoresPorNome(nome);
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado.");
            return;
        }

        for (Fornecedor f : fornecedores) {
            fornecedorToString(f);
        }
    }

    public void fornecedorToString(Fornecedor fornecedor){
        System.out.println("ID: " + fornecedor.getId());
        System.out.println("Nome: " + fornecedor.getNome());
        System.out.println("CNPJ: " + fornecedor.getCnpj());
        System.out.println("Telefone: " + fornecedor.getTelefone());
    }
}
