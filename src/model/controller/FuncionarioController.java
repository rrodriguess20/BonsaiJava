package model.controller;

import java.util.Scanner;

import model.entities.Funcionario;
import model.entities.Usuario;
import model.service.FuncionarioService;
import model.service.UsuarioService;

public class FuncionarioController {

    private FuncionarioService funcionarioService;
    private UsuarioService usuarioService;

    private final Scanner sc = new Scanner(System.in);

    public FuncionarioController(FuncionarioService funcionarioService, UsuarioService usuarioService) {
        this.funcionarioService = funcionarioService;
        this.usuarioService = usuarioService;
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

        listarFuncionarios();

        System.out.println("Digite o ID do Funcionário que deseja atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        var funcionario = funcionarioService.buscarFuncionarioPorId(id);
        Usuario usuario;
        if(funcionario == null){
            System.out.println("Funcionário não encontrado!");
            return;
        }
        usuario = usuarioService.buscarUsuarioPorId(funcionario.getIdUsuario());
        System.out.println("Digite o novo nome: (Vazio para não alterar) ");
        String nome =  sc.nextLine();
        if(!nome.isEmpty()) {
            usuario.setNome(nome);
        }
        System.out.println("Digite o novo email: (Vazio para não alterar) ");
        String email =  sc.nextLine();
        if(!email.isEmpty()) {
            usuario.setEmail(email);
        }
        System.out.println("Digite a nova senha: (Vazio para não alterar) ");

        String senha =  sc.nextLine();
        if(!senha.isEmpty()) {
            usuario.setSenha(senha);
        }
        usuarioService.atualizarUsuario(usuario);

        System.out.println("Digite o novo cargo: (Vazio para não alterar) ");
        String cargo =  sc.nextLine();
        if(!cargo.isEmpty()) {
            funcionario.setCargo(cargo);
        }
        funcionarioService.atualizarFuncionario(funcionario);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    public void listarFuncionarios(){
        var funcionarios = funcionarioService.listarFuncionarios();
        for(var func : funcionarios){
            funcionarioToString(func);
            System.out.println("-------------------");
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
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);
        if(funcionario == null){
            System.out.println("Funcionário não encontrado!");
            return;
        }
        funcionarioToString(funcionario);
    }

    public void funcionarioToString(Funcionario funcionario) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(funcionario.getIdUsuario());
        System.out.println("ID: " + funcionario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.println("Telefone: " + funcionario.getTelefone());
    }

}
