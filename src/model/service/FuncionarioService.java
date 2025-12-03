package model.service;


import model.entities.Usuario;
import model.entities.Funcionario;

import java.util.List;

import model.dao.FuncionarioDAO;
import model.dao.UsuarioDAO;



public class FuncionarioService {


    private FuncionarioDAO funcionarioDAO;
    private UsuarioService usuarioService;

    public FuncionarioService(FuncionarioDAO funcionarioDAO, UsuarioService usuarioService) {
        this.funcionarioDAO = funcionarioDAO;
        this.usuarioService = usuarioService;

    }

    public void cadastrarFuncionario(String nome, String senha, String email, String cargo) {
       
        Usuario usuario = usuarioService.cadastrarUsuario(nome, senha, email);
        Funcionario funcionario = new Funcionario(cargo, usuario.getId());
        funcionarioDAO.insert(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        funcionarioDAO.update(funcionario);
    }

    public void deletarFuncionario(int id) {
        funcionarioDAO.deleteById(id);
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return funcionarioDAO.findById(id);
    }

    //Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.findAll();
    }
}
