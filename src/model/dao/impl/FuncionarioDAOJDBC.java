package model.dao.impl;

import db.DB;
import model.entities.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.dao.FuncionarioDAO;

public class FuncionarioDAOJDBC implements model.dao.FuncionarioDAO {
    
    public void insert(Funcionario funcionario) {
        // Implementation for inserting a Funcionario into the database
        String sql = "INSERT INTO funcionario (nome, cargo, usuario, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setInt(3, funcionario.getUsuario().getId());
            stmt.setString(4, funcionario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
    }
}
