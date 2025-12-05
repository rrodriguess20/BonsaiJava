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
        Usuario usuario = new Usuario(nome, email, senha);
        usuarioDAO.insert(usuario);
        return usuario;
    }

    //Read
    public Usuario buscarUsuario(Usuario usuario){
        return usuarioDAO.findById(usuario.getId());
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
    public void excluirUsuarioQ(Usuario usuario){
        usuarioDAO.deleteById(usuario.getId());
    }
    
}
