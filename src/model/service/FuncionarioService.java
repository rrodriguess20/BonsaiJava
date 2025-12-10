package model.service;


import model.entities.Usuario;
import model.entities.Funcionario;
import java.util.List;
import model.dao.FuncionarioDAO;

public class FuncionarioService {


    private FuncionarioDAO funcionarioDAO;
    private UsuarioService usuarioService;

    public FuncionarioService(FuncionarioDAO funcionarioDAO, UsuarioService usuarioService) {
        this.funcionarioDAO = funcionarioDAO;
        this.usuarioService = usuarioService;

    }
    //Create
    public void cadastrarFuncionario(String nome, String senha, String email, String cargo) {
       
        Usuario usuario = usuarioService.cadastrarUsuario(nome, senha, email);
        Funcionario funcionario = new Funcionario(cargo, usuario.getId());
        funcionarioDAO.insert(funcionario);
    }
    // Update
    public void atualizarFuncionario(Funcionario funcionario) {
        funcionarioDAO.update(funcionario);
    }
    // Delete
    public void deletarFuncionario(int id) {
        int usuarioId = funcionarioDAO.findById(id).getIdUsuario();
        funcionarioDAO.deleteById(id);
        usuarioService.excluirUsuario(usuarioService.buscarUsuarioPorId(usuarioId));
    }

    //Read
    public Funcionario buscarFuncionarioPorId(int id) {
        return funcionarioDAO.findById(id);
    }

    //Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.findAll();
    }
}
