package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Produto;

import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException;

public class ProdutoDAOJDBC implements model.dao.ProdutoDAO{

    private final Connection conn;

    public ProdutoDAOJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void insert(Produto produto){
        String sql = "INSERT INTO produto (nome, preco, categoria) VALUES (?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getCategoria());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new DatabaseException("Erro ao inserir produto", e);
        }
    }

    public void update(Produto produto){
        String sql = "UPDATE produto SET nome = ?, preco = ?, categoria = ? WHERE id = ? ";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getId());
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Produto não encontrado para atualização: " + produto.getId());
            }
        }catch(SQLException e){
            throw new DatabaseException("Erro ao atualizar produto", e);
        }
    }

    public void deleteById(int id){
        String sql = "DELETE FROM produto WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            if(stmt.executeUpdate() == 0){
                throw new EntityNotFoundException("Produto não encontrado para exclusão: " + id);
            }
        }catch(SQLException e){
            throw new DatabaseException("Erro ao deletar produto", e);
        }
    }

    public Produto findById(int id){

        String sql = "SELECT  * FROM produto WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setCategoria(rs.getString("categoria"));
                    return produto; 
                }
            }
            throw new EntityNotFoundException("Produto não encontrado: " + id);
        }catch(SQLException e){
            throw new DatabaseException("Erro ao buscar produto", e);
        }
    }

    public List<Produto> findAll(){
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try(PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setCategoria(rs.getString("categoria"));
                produtos.add(produto);
            }
        }catch(SQLException e){
            throw new DatabaseException("Erro ao listar produtos", e);
        }
        return produtos;
    }



}
