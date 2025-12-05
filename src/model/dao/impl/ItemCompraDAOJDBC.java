package model.dao.impl;

import model.dao.ItemCompraDAO;
import model.entities.ItemCompra;
import java.util.List; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemCompraDAOJDBC implements ItemCompraDAO {
    

    Connection conn;

    public ItemCompraDAOJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(ItemCompra itemCompra){
        String sql = "INSERT INTO item_compra (id_produto, id_compra, quantidade, preco_unitario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemCompra.getIdProduto());
            stmt.setInt(2, itemCompra.getIdCompra());
            stmt.setInt(3, itemCompra.getQuantidade());
            stmt.setDouble(4, itemCompra.getPrecoUnitario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(ItemCompra itemCompra) {
        String sql = "UPDATE item_compra SET id_produto = ?, id_compra = ?, quantidade = ?, preco_unitario = ? WHERE id_item_compra = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemCompra.getIdProduto());
            stmt.setInt(2, itemCompra.getIdCompra());
            stmt.setInt(3, itemCompra.getQuantidade());
            stmt.setDouble(4, itemCompra.getPrecoUnitario());
            stmt.setInt(5, itemCompra.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM item_compra WHERE id_item_compra = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
    }
    @Override
    public ItemCompra findById(int id) {
        
        String sql = "SELECT * FROM item_compra WHERE id_item_compra = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                ItemCompra itemCompra = new ItemCompra();
                itemCompra.setId(rs.getInt("id"));
                itemCompra.setIdProduto(rs.getInt("id_produto"));
                itemCompra.setIdCompra(rs.getInt("id_compra"));
                itemCompra.setQuantidade(rs.getInt("quantidade"));
                itemCompra.setPrecoUnitario(rs.getDouble("preco_unitario"));
                return itemCompra;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<ItemCompra> findAll() {
        String sql = "SELECT * FROM item_compra";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            List<ItemCompra> itemCompras = new java.util.ArrayList<>();
            while (rs.next()) {
                ItemCompra itemCompra = new ItemCompra();
                itemCompra.setId(rs.getInt("id_item_compra"));
                itemCompra.setIdProduto(rs.getInt("id_produto"));
                itemCompra.setIdCompra(rs.getInt("id_compra"));
                itemCompra.setQuantidade(rs.getInt("quantidade"));
                itemCompra.setPrecoUnitario(rs.getDouble("preco_unitario"));
                itemCompras.add(itemCompra);
            }
            return itemCompras;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
