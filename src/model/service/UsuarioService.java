package model.service;

import model.dao.UsuarioDAO;
import model.entities.Usuario;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    //Create
    public Usuario cadastrarUsuario(String nome, String senha, String email) {
        Usuario usuario = new Usuario(nome, senha, email);
        usuarioDAO.insert(usuario);
        return usuario;
    }

    //Read
    public Usuario buscarUsuarioPorId(int id){
        return usuarioDAO.findById(id);
    }

    public List<Usuario> listarTodos(){
        List<Usuario> listaUsuarios = usuarioDAO.findAll();
        return listaUsuarios;
    }

    //Update
    public void atualizarUsuario(Usuario usuario){
        usuarioDAO.update(usuario);
    }

    //Delete
    public void excluirUsuario(Usuario usuario){
        usuarioDAO.deleteById(usuario.getId());
    }
    
}
