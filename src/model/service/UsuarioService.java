package model.service;

import model.dao.UsuarioDAO;
import model.entities.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    //Create
    public void cadastrarUsuario(String nome, String senha, String email) {
        Usuario usuario = new Usuario(nome, email, senha);
        usuarioDAO.insert(usuario);
    }

    //Read
    public Usuario buscarUsuario(int id){
        return usuarioDao.findById(id);
    }

    //Update
    public void atualizarUsuario(Usuario usuario){
        usuarioDAO.update(usuario);
    }

    //Delete
    public void excluirUsuario(Usuario usuario){
        usuarioDAO.delete
    }



    
}
