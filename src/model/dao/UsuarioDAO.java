package model.dao;

import model.entities.Usuario;
import java.util.List;
public interface UsuarioDAO {
    void insert(Usuario usuario);
    void update(Usuario usuario);
    void deleteById(int id);
    Usuario findById(int id);
    List<Usuario> findAll();
}