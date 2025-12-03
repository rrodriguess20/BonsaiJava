package model.controller;

import java.util.Scanner;
import model.service.FuncionarioService;
import model.dao.DaoFactory;

public class FuncionarioController {

    private FuncionarioService funcionarioService;

    private final Scanner sc = new Scanner(System.in);

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = new FuncionarioService(
            DaoFactory.createUsuarioDAO(),
            DaoFactory.createFuncionarioDAO()
        );
    }

    public void cadastrarNovoFuncionario(){
        
        System.out.println("Digite o nome do novo Funcionário: ");
        String nome = sc.nextLine();
        System.out.println("Digite o cargo: ");
        String cargo = sc.nextLine();
        System.out.println("Digite o email: ");
        String email = sc.nextLine();
        System.out.println("Digite a senha: ");
        String senha = sc.nextLine();

        funcionarioService.cadastrarFuncionario(nome, senha, email, cargo);

    }

    public void atualizarFuncionario(){

        

    }

    pub



}
