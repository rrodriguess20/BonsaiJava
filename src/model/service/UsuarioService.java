package model.service;

import model.dao.UsuarioDAO;
import model.entities.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void cadastrarUsuario(String nome, String senha, String email) {
        Usuario usuario = new Usuario(nome, email, senha);
        usuarioDAO.insert(usuario);
    }

    
    
}
