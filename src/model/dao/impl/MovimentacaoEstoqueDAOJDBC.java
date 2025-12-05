package model.dao.impl;

import model.dao.MovimentacaoEstoqueDAO;
import model.entities.MovimentacaoEstoque;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimentacaoEstoqueDAOJDBC implements MovimentacaoEstoqueDAO {
    
    private Connection conn;

    public MovimentacaoEstoqueDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(MovimentacaoEstoque movimentacao) {
        String sql = "INSERT INTO movimentacao_estoque (produto_id, quantidade, tipo, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movimentacao.getProdutoId());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setString(3, movimentacao.getTipo());
            stmt.setDate(4, new java.sql.Date(movimentacao.getData().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MovimentacaoEstoque> findAll() {
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao_estoque";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
                movimentacao.setId(rs.getInt("id"));
                movimentacao.setProdutoId(rs.getInt("produto_id"));
                movimentacao.setQuantidade(rs.getInt("quantidade"));
                movimentacao.setTipo(rs.getString("tipo"));
                movimentacao.setData(rs.getDate("data"));
                movimentacoes.add(movimentacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimentacoes;
    }



}
