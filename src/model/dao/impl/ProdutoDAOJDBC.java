package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Produto;

public class ProdutoDAOJDBC implements model.dao.ProdutoDAO{

    Connection conn;

    private ProdutoDAOJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void insert(Produto produto){
        String sql = "INSERT INTO produto (nome, preco, categoria, quantidadeEstoque) VALUES (?,?,?,?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Erro ao cadastrar produto: " + e);
        }
    }

    public void update(Produto produto){
        String sql = "UPDATE produto SET nome = ?, preco = ?, categoria = ?, quantitadeEstoque = ? WHERE id = ? ";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar produto: " + e);
        }
    }

    public void deleteById(int id){
        String sql = "DELETE FROM produto WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Erro ao deletar produto: " + e);
        }
    }

    public Produto findById(int id){

        String sql = "SELECT  * FROM produto WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                return produto; 
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar produto: " + e);
        }
        return null;
    }

    public List<Produto> findAll(){
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                produtos.add(produto);
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao listar produtos: " + e);
        }
        return produtos;
    }



}
