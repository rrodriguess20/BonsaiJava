package model.service;

import model.dao.ProdutoDAO;
import model.entities.Produto;
import java.util.List;

public class ProdutoService {
    
    private ProdutoDAO produtoDAO;

    //Constructor
    public ProdutoService(ProdutoDAO produtoDAO){
        this.produtoDAO = produtoDAO;
    }

    //Create
    public void cadastrarProduto(String nome, String categoria, double preco){
        Produto produto = new Produto(nome, categoria, preco);
        produtoDAO.insert(produto);
    }
    //Update
    public void atualizarProduto(Produto produto){
        produtoDAO.update(produto);
    }
    //Delete
    public void deletarProduto(int id){
        produtoDAO.deleteById(id);
    }
    //Read
    public Produto buscarProdutoPorId(int id){
        return produtoDAO.findById(id);
    }
    
    public List<Produto> listarTodosProdutos(){
        return produtoDAO.findAll();
    }

}
