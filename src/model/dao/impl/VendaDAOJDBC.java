package model.dao.impl;

import model.entities.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException;

public class VendaDAOJDBC implements model.dao.VendaDAO {

    private Connection conn;

    public VendaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Venda venda) {
        String sql = "INSERT INTO venda (id_funcionario, data_venda, total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, venda.getFuncionario().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(venda.getDataVenda()));
            stmt.setDouble(3, venda.getTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir venda", e);
        }
    }

    public void update(Venda venda) {
        String sql = "UPDATE venda SET id_funcionario = ?, data_venda = ?, total = ? WHERE id_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getFuncionario().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(venda.getDataVenda()));
            stmt.setDouble(3, venda.getTotal());
            stmt.setInt(4, venda.getId());
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Venda não encontrada para atualização: " + venda.getId());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar venda", e);
        }
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM venda WHERE id_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Venda não encontrada para exclusão: " + id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar venda", e);
        }
    }

    public Venda findById(int id) {
        String sql = "SELECT * FROM venda WHERE id_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    Venda venda = new Venda();
                    venda.setId(rs.getInt("id_venda"));
                    venda.setDataVenda(rs.getTimestamp("data_venda").toLocalDateTime());
                    venda.setTotal(rs.getDouble("total"));               
                    FuncionarioDAOJDBC funcionarioDAO = new FuncionarioDAOJDBC(conn);
                    venda.setFuncionario(funcionarioDAO.findById(rs.getInt("id_funcionario")));
                    return venda;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar venda por ID", e);
        }
        throw new EntityNotFoundException("Venda", id);
    }

    @Override
    public List<Venda> findAll() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id_venda"));
                venda.setDataVenda(rs.getTimestamp("data_venda").toLocalDateTime());
                venda.setTotal(rs.getDouble("total"));               
                FuncionarioDAOJDBC funcionarioDAO = new FuncionarioDAOJDBC(conn);
                venda.setFuncionario(funcionarioDAO.findById(rs.getInt("id_funcionario")));
                vendas.add(venda);
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todas as vendas", e);
        }
        return vendas;
    }
}
