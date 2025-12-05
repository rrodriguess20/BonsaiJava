package model.controller;

import java.util.Scanner;
import model.service.FuncionarioService;


public class FuncionarioController {

    private FuncionarioService funcionarioService;

    private final Scanner sc = new Scanner(System.in);

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public void cadastrarNovoFuncionario(){
        
        System.out.println("Digite o nome do novo Funcionário: ");
        String nome = sc.nextLine();
        System.out.println("Digite o cargo: ");
        String cargo = sc.nextLine();
        System.out.println("Digite o email: ");
        String email = sc.nextLine();
        System.out.println("Digite uma senha: ");
        String senha = sc.nextLine();

        funcionarioService.cadastrarFuncionario(nome, senha, email, cargo);

    }

    public void atualizarFuncionario(){
        
        System.out.println("Digite o ID do Funcionário que deseja atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        var funcionario = funcionarioService.buscarFuncionarioPorId(id);
        if(funcionario == null){
            System.out.println("Funcionário não encontrado!");
            return;
        }
        System.out.println("Digite o novo cargo: ");
        String cargo =  sc.nextLine();
        funcionario.setCargo(cargo);
        funcionarioService.atualizarFuncionario(funcionario);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    public void listarFuncionarios(){
        var funcionarios = funcionarioService.listarFuncionarios();
        for(var func : funcionarios){
            System.out.println(func);
        }
    }

    public void deletarFuncionario(){
        System.out.println("Digite o ID do Funcionário que deseja deletar: ");
        int id = Integer.parseInt(sc.nextLine());
        funcionarioService.deletarFuncionario(id);
        System.out.println("Funcionário deletado com sucesso!");
    }

    public void buscarFuncionarioPorId(){
        System.out.println("Digite o ID do Funcionário que deseja buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        var funcionario = funcionarioService.buscarFuncionarioPorId(id);
        if(funcionario == null){
            System.out.println("Funcionário não encontrado!");
            return;
        }
        System.out.println(funcionario);
    }



}
