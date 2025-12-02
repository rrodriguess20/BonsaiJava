package model.dao.impl;

import model.entities.ItemVenda;
import model.dao.ItemVendaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;  


public class ItemVendaDAOJDBC implements ItemVendaDAO {


    
    private Connection conn;

    public ItemVendaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ItemVenda itemVenda) {
        String sql = "INSERT INTO item_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemVenda.getId_venda());
            stmt.setInt(2, itemVenda.getId_produto());
            stmt.setInt(3, itemVenda.getQuantidade());
            stmt.setDouble(4, itemVenda.getPreco_unitario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ItemVenda itemVenda) {
        String sql = "UPDATE item_venda SET venda_id = ?, produto_id = ?, quantidade = ?, preco_unitario = ? WHERE id_item_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemVenda.getId_venda());
            stmt.setInt(2, itemVenda.getId_produto());
            stmt.setInt(3, itemVenda.getQuantidade());
            stmt.setDouble(4, itemVenda.getPreco_unitario());
            stmt.setInt(5, itemVenda.getId_item_venda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM item_venda WHERE id_item_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ItemVenda findById(int id) {
        String sql = "SELECT * FROM item_venda WHERE id_item_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ItemVenda(
                    rs.getInt("id_item_venda"),
                    rs.getInt("venda_id"),
                    rs.getInt("produto_id"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco_unitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemVenda> findAll() {
        List<ItemVenda> itemVendas = new ArrayList<>();
        String sql = "SELECT * FROM item_venda";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ItemVenda itemVenda = new ItemVenda();
                itemVendas.add(itemVenda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return itemVendas;  
    }
}