package model.dao.impl;

import db.DB;
import model.entities.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.dao.VendaDAO;


public class VendaDAOJDBC implements model.dao.VendaDAO {

    private Connection conn;

    public VendaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void novaVenda(Venda venda) {
        String sql = "INSERT INTO venda (id_usurio, id_funcionario, data_venda, valor_total) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setDouble(2, venda.getValorTotal());
            
            stmt.setInt(4, venda.getFuncionario().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Venda> listarTodas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id_venda"));
                venda.setData(rs.getDate("data"));
                venda.setValorTotal(rs.getDouble("valor_total"));
                // Assuming ClienteDAOJDBC and FuncionarioDAOJDBC have been implemented
                ClienteDAOJDBC clienteDAO = new ClienteDAOJDBC(conn);
                venda.setCliente(clienteDAO.findById(rs.getInt("id_cliente")));
                FuncionarioDAOJDBC funcionarioDAO = new FuncionarioDAOJDBC(conn);
                venda.setFuncionario(funcionarioDAO.findById(rs.getInt("id_funcionario")));
                vendas.add(venda);
            }
        }
    }
}
