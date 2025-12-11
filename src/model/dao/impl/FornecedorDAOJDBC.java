package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Fornecedor;
import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException;

public class FornecedorDAOJDBC implements model.dao.FornecedorDAO {

    private final Connection conn;
    public FornecedorDAOJDBC(Connection conn) {
        this.conn = conn;
    }
    
    public void insert(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (nome, cnpj, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir fornecedor", e);
        }    
    }

    public void update(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setInt(4, fornecedor.getId());
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Fornecedor não encontrado para atualização: " + fornecedor.getId());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar fornecedor", e);
        }

    }

    public void deleteById(int id) {
        String sql = "DELETE FROM fornecedor WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Fornecedor não encontrado para exclusão: " + id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar fornecedor", e);
        }
    }

    public Fornecedor findById(int id) {
        String sql = "SELECT * FROM fornecedor WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(rs.getInt("id"));
                    fornecedor.setNome(rs.getString("nome"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setTelefone(rs.getString("telefone"));
                    return fornecedor;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar fornecedor por ID", e);
        }
        throw new EntityNotFoundException("Fornecedor", id);
    }


    public List<Fornecedor> findAll() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedores.add(fornecedor);
            }
            return fornecedores;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao listar fornecedores", e);
        }
    }

    public Fornecedor findByCnpj(String cnpj) {
        String sql = "SELECT * FROM fornecedor WHERE cnpj = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(rs.getInt("id"));
                    fornecedor.setNome(rs.getString("nome"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setTelefone(rs.getString("telefone"));
                    return fornecedor;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar fornecedor por CNPJ", e);
        }

        throw new EntityNotFoundException("Fornecedor com CNPJ não encontrado: " + cnpj);
    }

    public List<Fornecedor> findByName(String nome) {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor WHERE nome LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            try(ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedores.add(fornecedor);
            }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar fornecedores por nome", e);
        }
        return fornecedores;
    }
}
