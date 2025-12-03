package model.service;


import model.entities.Usuario;
import model.entities.Funcionario;
import model.dao.FuncionarioDAO;
import model.dao.UsuarioDAO;



public class FuncionarioService {

    private UsuarioDAO usuarioDAO;
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioService(UsuarioDAO usuarioDAO, FuncionarioDAO funcionarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.funcionarioDAO = funcionarioDAO;
    }

    public void cadastrarFuncionario(String nome, String senha, String email, String cargo) {
       
        Usuario usuario = new Usuario(nome, email, senha);
        usuarioDAO.insert(usuario);
        Funcionario funcionario = new Funcionario(cargo, usuario.getId());
        funcionarioDAO.insert(funcionario);
    }
}
