package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entities.Compra;

public class CompraDAOJDBC implements model.dao.CompraDAO {

    private Connection conn;

    public CompraDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Compra compra) {
        String sql = "INSERT INTO compra (id_fornecedor, data_compra, valor_total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, compra.getIdFornecedor());
            stmt.setDate(2, java.sql.Date.valueOf(compra.getDataCompra()));
            stmt.setDouble(3, compra.getValorTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(model.entities.Compra compra) {
        String sql = "UPDATE compra SET id_fornecedor = ?, data_compra = ?, valor_total = ? WHERE id_compra = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, compra.getIdFornecedor());
            stmt.setDate(2, java.sql.Date.valueOf(compra.getDataCompra()));
            stmt.setDouble(3, compra.getValorTotal());
            stmt.setInt(4, compra.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM compra WHERE id_compra = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public model.entities.Compra findById(int id) {
        String sql = "SELECT * FROM compra WHERE id_compra = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                model.entities.Compra compra = new model.entities.Compra();
                compra.setId(rs.getInt("id_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setDataCompra(rs.getDate("data_compra").toLocalDate());
                compra.setValorTotal(rs.getDouble("valor_total"));
                return compra;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Compra> findByFornecedorId(int id_fornecedor) {
        String sql = "SELECT * FROM compra WHERE id_fornecedor = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_fornecedor);
            ResultSet rs = stmt.executeQuery();
            List<Compra> compras = new ArrayList<>();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setDataCompra(rs.getDate("data_compra").toLocalDate());
                compra.setValorTotal(rs.getDouble("valor_total"));
                compras.add(compra);
            }
            return compras;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  

    @Override
    public java.util.List<model.entities.Compra> findAll() {
        String sql = "SELECT * FROM compra";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Compra> compras = new ArrayList<>();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setDataCompra(rs.getDate("data_compra").toLocalDate());
                compra.setValorTotal(rs.getDouble("valor_total"));
                compras.add(compra);
            }
            return compras;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


    
