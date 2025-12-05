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
        String sql = "INSERT INTO funcionario (cargo) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCargo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET cargo = ? WHERE id_funcionario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCargo());
            stmt.setInt(2, funcionario.getId());
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
        }Q
    }

    public Funcionario findById(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id_funcionario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setIdUsuario(rs.getInt("id_usuario"));
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
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setIdUsuario(rs.getInt("id_usuario"));
                    funcionarios.add(funcionario);
                }
                return funcionarios;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

     
}
