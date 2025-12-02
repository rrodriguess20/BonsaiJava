package model.dao.impl;


import model.entities.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class VendaDAOJDBC implements model.dao.VendaDAO {

    private Connection conn;

    public VendaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Venda venda) {
        String sql = "INSERT INTO venda (id_usurio, id_funcionario, data_venda, valor_total) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getUsuario().getId());
            stmt.setInt(2, venda.getFuncionario().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(venda.getDataVenda()));
            stmt.setDouble(4, venda.getValorTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Venda venda) {
        String sql = "UPDATE venda SET id_usuario = ?, id_funcionario = ?, data_venda = ?, valor_total = ? WHERE id_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getUsuario().getId());
            stmt.setInt(2, venda.getFuncionario().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(venda.getDataVenda()));
            stmt.setDouble(4, venda.getValorTotal());
            stmt.setInt(5, venda.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM venda WHERE id_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Venda findById(int id) {
        String sql = "SELECT * FROM venda WHERE id_venda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id_venda"));
                venda.setDataVenda(rs.getTimestamp("data_venda").toLocalDateTime());
                venda.setValorTotal(rs.getDouble("valor_total"));               
                UsuarioDAOJDBC usuarioDAO = new UsuarioDAOJDBC(conn);
                venda.setUsuario(usuarioDAO.findById(rs.getInt("id_usuario")));
                FuncionarioDAOJDBC funcionarioDAO = new FuncionarioDAOJDBC(conn);
                venda.setFuncionario(funcionarioDAO.findById(rs.getInt("id_funcionario")));
                return venda;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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
                venda.setValorTotal(rs.getDouble("valor_total"));               
                UsuarioDAOJDBC usuarioDAO = new UsuarioDAOJDBC(conn);
                venda.setUsuario(usuarioDAO.findById(rs.getInt("id_usuario")));
                FuncionarioDAOJDBC funcionarioDAO = new FuncionarioDAOJDBC(conn);
                venda.setFuncionario(funcionarioDAO.findById(rs.getInt("id_funcionario")));
                vendas.add(venda);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vendas;
    }
}
