package model.controller;

import java.util.Scanner;

import model.entities.Funcionario;
import model.entities.Usuario;
import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException;
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
        try{
            System.out.println("Digite o nome do novo Funcionário: ");
            String nome = sc.nextLine();
            System.out.println("Digite o cargo: ");
            String cargo = sc.nextLine();
            System.out.println("Digite o email: ");
            String email = sc.nextLine();
            System.out.println("Digite uma senha: ");
            String senha = sc.nextLine();

            funcionarioService.cadastrarFuncionario(nome, senha, email, cargo);
            System.out.println("Funcionário cadastrado com sucesso!");
        }catch(DatabaseException e){
            System.out.println("Erro ao cadastrar funcionário!");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao cadastrar funcionário!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void atualizarFuncionario(){
        listarFuncionarios();

        System.out.println("Digite o ID do Funcionário que deseja atualizar: ");
        final int id;
        try{
            id = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("ID inválido.");
            return;
        }

        Funcionario funcionario;
        Usuario usuario;
        try{
            funcionario = funcionarioService.buscarFuncionarioPorId(id);
            usuario = usuarioService.buscarUsuarioPorId(funcionario.getIdUsuario());
        }catch(EntityNotFoundException e){
            System.out.println("Funcionário ou usuário associado não encontrado!");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }catch(DatabaseException e){
            System.out.println("Erro ao buscar dados do funcionário!");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }catch(Exception e){
            System.out.println("Ocorreu um erro inesperado ao buscar os dados.");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }

        if(funcionario == null || usuario == null){
            System.out.println("Funcionário não encontrado!");
            return;
        }

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
        System.out.println("Digite o novo cargo: (Vazio para não alterar) ");
        String cargo =  sc.nextLine();
        if(!cargo.isEmpty()) {
            funcionario.setCargo(cargo);
        }

        try{
            usuarioService.atualizarUsuario(usuario);
            funcionarioService.atualizarFuncionario(funcionario);
            System.out.println("Funcionário atualizado com sucesso!");
        }catch(EntityNotFoundException e){
            System.out.println("Funcionário ou usuário não encontrado durante a atualização!");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(DatabaseException e){
            System.out.println("Erro ao atualizar funcionário! ");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Ocorreu um erro inesperado ao atualizar o funcionário.");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void listarFuncionarios(){
        try{
            var funcionarios = funcionarioService.listarFuncionarios();
            if(funcionarios.isEmpty()){
                System.out.println("Nenhum funcionário cadastrado.");
                return;
            }
            for(var func : funcionarios){
                funcionarioToString(func);
                System.out.println("-------------------");
            }
        }catch(DatabaseException e){
            System.out.println("Erro ao listar funcionários!");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao listar funcionários!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void deletarFuncionario(){
        System.out.println("Digite o ID do Funcionário que deseja deletar: ");
        final int id;
        try{
            id = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("ID inválido.");
            return;
        }
        try{
            funcionarioService.deletarFuncionario(id);
            System.out.println("Funcionário deletado com sucesso!");
        }catch(EntityNotFoundException e){
            System.out.println("Funcionário não encontrado!");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(DatabaseException e){
            System.out.println("Erro ao deletar funcionário! ");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao deletar funcionário!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void buscarFuncionarioPorId(){
        System.out.println("Digite o ID do Funcionário que deseja buscar: ");
        final int id;
        try{
            id = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("ID inválido.");
            return;
        }
        try{
            Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);
            funcionarioToString(funcionario);
        }catch(EntityNotFoundException e){
            System.out.println("Funcionário não encontrado!");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(DatabaseException e){
            System.out.println("Erro ao buscar funcionário! ");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Ocorreu um erro inesperado ao buscar o funcionário.");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void funcionarioToString(Funcionario funcionario) {
        try{
            Usuario usuario = usuarioService.buscarUsuarioPorId(funcionario.getIdUsuario());
            System.out.println("ID: " + funcionario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Cargo: " + funcionario.getCargo());
            System.out.println("Telefone: " + funcionario.getTelefone());
        }catch(EntityNotFoundException e){
            System.out.println("Usuário associado ao funcionário não encontrado.");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(DatabaseException e){
            System.out.println("Erro ao buscar usuário associado ao funcionário.");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao exibir dados do funcionário.");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

}
