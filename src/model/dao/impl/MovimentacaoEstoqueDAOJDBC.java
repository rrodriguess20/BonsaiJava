package model.dao.impl;

import model.dao.MovimentacaoEstoqueDAO;
import model.entities.MovimentacaoEstoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoEstoqueDAOJDBC implements MovimentacaoEstoqueDAO {
    
    private Connection conn;

    public MovimentacaoEstoqueDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(MovimentacaoEstoque movimentacao) {
        String sql = "INSERT INTO movimentacao_estoque (produto_id, quantidade, tipo, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movimentacao.getIdProduto());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setString(3, movimentacao.getTipoMovimentacao());
            stmt.setTimestamp(4, toTimestamp(movimentacao.getDataMovimentacao()));
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
                movimentacao.setIdProduto(rs.getInt("produto_id"));
                movimentacao.setQuantidade(rs.getInt("quantidade"));
                movimentacao.setTipoMovimentacao(rs.getString("tipo"));
                movimentacao.setDataMovimentacao(toLocalDateTime(rs.getTimestamp("data")));
                movimentacoes.add(movimentacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimentacoes;
    }

    @Override
    public MovimentacaoEstoque findById(int id) {
        String sql = "SELECT * FROM movimentacao_estoque WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
                movimentacao.setId(rs.getInt("id"));
                movimentacao.setIdProduto(rs.getInt("produto_id"));
                movimentacao.setQuantidade(rs.getInt("quantidade"));
                movimentacao.setTipoMovimentacao(rs.getString("tipo"));
                movimentacao.setDataMovimentacao(toLocalDateTime(rs.getTimestamp("data")));
                return movimentacao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(MovimentacaoEstoque movimentacao) {
        String sql = "UPDATE movimentacao_estoque SET produto_id = ?, quantidade = ?, tipo = ?, data = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movimentacao.getIdProduto());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setString(3, movimentacao.getTipoMovimentacao());
            stmt.setTimestamp(4, toTimestamp(movimentacao.getDataMovimentacao()));
            stmt.setInt(5, movimentacao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM movimentacao_estoque WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Timestamp toTimestamp(LocalDateTime dateTime) {
        return dateTime == null ? null : Timestamp.valueOf(dateTime);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
