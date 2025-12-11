package model.dao.impl;

import model.entities.ItemVenda;
import model.dao.ItemVendaDAO;
import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;  


public class ItemVendaDAOJDBC implements ItemVendaDAO {


    
    private final Connection conn;

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
            throw new DatabaseException("Erro ao inserir item de venda", e);
        }
    }

    public void update(ItemVenda itemVenda) {
        String sql = "UPDATE item_venda SET venda_id = ?, produto_id = ?, quantidade = ?, preco_unitario = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemVenda.getId_venda());
            stmt.setInt(2, itemVenda.getId_produto());
            stmt.setInt(3, itemVenda.getQuantidade());
            stmt.setDouble(4, itemVenda.getPreco_unitario());
            stmt.setInt(5, itemVenda.getId());
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Item de venda não encontrado para atualização: " + itemVenda.getId());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar item de venda", e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM item_venda WHERE id_item_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Item de venda não encontrado para exclusão: " + id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar item de venda", e);
        }
    }

    public ItemVenda findById(int id) {
        String sql = "SELECT * FROM item_venda WHERE id_item_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    ItemVenda itemVenda = new ItemVenda();
                    itemVenda.setId(rs.getInt("id_item_venda"));
                    itemVenda.setId_venda(rs.getInt("venda_id"));
                    itemVenda.setId_produto(rs.getInt("produto_id"));
                    itemVenda.setQuantidade(rs.getInt("quantidade"));
                    itemVenda.setPreco_unitario(rs.getDouble("preco_unitario"));
                    itemVenda.setSubtotal(rs.getDouble("subtotal"));
                    return itemVenda;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar item de venda por ID", e);
        }
        throw new EntityNotFoundException("ItemVenda", id);
    }

    @Override
    public List<ItemVenda> findAll() {
        List<ItemVenda> itemVendas = new ArrayList<>();
        String sql = "SELECT * FROM item_venda";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setId(rs.getInt("id_item_venda"));
                itemVenda.setId_venda(rs.getInt("venda_id"));
                itemVenda.setId_produto(rs.getInt("produto_id"));
                itemVenda.setQuantidade(rs.getInt("quantidade"));
                itemVenda.setPreco_unitario(rs.getDouble("preco_unitario"));
                itemVenda.setSubtotal(rs.getDouble("subtotal"));
                itemVendas.add(itemVenda);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao listar itens de venda", e);
        }   
        return itemVendas;  
    }
}