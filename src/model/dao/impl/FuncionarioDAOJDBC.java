package model.dao.impl;


import model.entities.Funcionario;
import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOJDBC implements model.dao.FuncionarioDAO {

    private final Connection conn;

    public FuncionarioDAOJDBC(Connection conn) {
        this.conn = conn;
        
    }

    public void insert(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cargo, id_usuario) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCargo());
            stmt.setInt(2, funcionario.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir funcionário no banco de dados", e);
        }
    }
    
    public void update(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET cargo = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCargo());
            stmt.setInt(2, funcionario.getId());
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Funcionário não encontrado para atualização: " + funcionario.getId());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar funcionário no banco de dados", e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Funcionário não encontrado para exclusão: " + id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar funcionário no banco de dados", e);
        }
    }

    public Funcionario findById(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setIdUsuario(rs.getInt("id_usuario"));
                return funcionario;
            }
        } catch (SQLException e) 
        {
            throw new DatabaseException("Erro ao buscar funcionário no banco de dados", e);
        }
        // Se não encontrar, lança exceção / não retorna nulo 
        throw new EntityNotFoundException("Funcionario", id);
        }
        
    

    public List<Funcionario> findAll(){
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try(Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setIdUsuario(rs.getInt("id_usuario"));
                    funcionarios.add(funcionario);
                }
                return funcionarios;
        }catch(SQLException e){
            throw new DatabaseException("Erro ao buscar funcionários no banco de dados", e);
        }
    }

     
}
