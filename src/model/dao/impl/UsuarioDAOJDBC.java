package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.UsuarioDAO;
import model.entities.Usuario;

import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException; 

public class UsuarioDAOJDBC implements UsuarioDAO {

    private final Connection conn;

    public UsuarioDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir usuário", e);
        }
    }

    @Override
    public void update(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Usuário não encontrado para atualização: " + usuario.getId());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar usuário", e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Usuário não encontrado para exclusão: " + id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar usuário", e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email, senha FROM usuario";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(instantiateUsuario(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todos os usuários", e);
        }
        return usuarios;
    }

    @Override
    public Usuario findById(int id) {
        String sql = "SELECT id, nome, email, senha FROM usuario WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return instantiateUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar usuário por ID", e);
        }
        throw new EntityNotFoundException("Usuario", id);
    }

    @Override
    public Usuario authenticate(String email, String senha) {
        String sql = "SELECT id, nome, email, senha FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return instantiateUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao autenticar usuário", e);
        }
        return null;
    }

    private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("senha"),
            rs.getString("email")
        );
    }
}
