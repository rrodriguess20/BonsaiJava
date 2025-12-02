package model.dao.impl;


import model.entities.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOJDBC implements model.dao.FuncionarioDAO {

    private Connection conn;

    public FuncionarioDAOJDBC(Connection conn) {
        this.conn = conn;
        
    }

    public void insert(Funcionario funcionario) {
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
    
    public void update(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, cargo = ?, usuario = ?, senha = ? WHERE id_funcionario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setInt(3, funcionario.getUsuario().getId());
            stmt.setString(4, funcionario.getSenha());
            stmt.setInt(5, funcionario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Funcionario findById(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                UsuarioDAOJDBC usuario = new UsuarioDAOJDBC(conn);
                funcionario.setUsuario(usuario.findById(rs.getInt("usuario")));
                funcionario.setSenha(rs.getString("senha"));
                return funcionario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Funcionario> findAll(){
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try(Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id_funcionario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCargo(rs.getString("cargo"));
                    UsuarioDAOJDBC usuario = new UsuarioDAOJDBC(conn);
                    funcionario.setUsuario(usuario.findById(rs.getInt("usuario")));
                    funcionario.setSenha(rs.getString("senha"));
                    funcionarios.add(funcionario);
                }
                return funcionarios;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

     
}
