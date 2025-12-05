package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.entities.Fornecedor;

public class FornecedorDAOJDBC implements model.dao.FornecedorDAO {

    Connection conn;
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
            e.printStackTrace();
        }    
    }

    public void update(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, telefone = ? WHERE id_fornecedor = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setInt(4, fornecedor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteById(int id) {
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Fornecedor findById(int id) {
        String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                return fornecedor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }


    public List<Fornecedor> findAll() {
        List<Fornecedor> fornecedores = new java.util.ArrayList<>();
        String sql = "SELECT * FROM fornecedor";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedores.add(fornecedor);
            }
            return fornecedores;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Fornecedor findByCnpj(String cnpj) {
        String sql = "SELECT * FROM fornecedor WHERE cnpj = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                return fornecedor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }

    
        return null;
    }

    public List<Fornecedor> findByName(String nome) {
        List<Fornecedor> fornecedores = new java.util.ArrayList<>();
        String sql = "SELECT * FROM fornecedor WHERE nome LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedores.add(fornecedor);
            }
            return fornecedores;
        } catch (SQLException e) {
            e.printStackTrace();



        }
        return null;
    }
}
