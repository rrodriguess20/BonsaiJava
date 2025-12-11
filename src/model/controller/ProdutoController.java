package model.controller;

import model.service.CompraService;
import model.service.ProdutoService;

import java.util.List;

import java.util.Scanner;

import model.entities.Produto;

public class ProdutoController {

    private ProdutoService produtoService;

    private final Scanner sc = new Scanner(System.in);

    public ProdutoController(ProdutoService produtoService) {
    this.produtoService = produtoService;
    }

    public void cadastrarProduto(){
        System.out.println("Insira o nome do produto:");
        String nome = sc.nextLine();
        System.out.println("Insira a categoria do produto:");
        String categoria = sc.nextLine();
        System.out.println("Insira o preço do produto:");
        double preco = sc.nextDouble();

        produtoService.cadastrarProduto(nome, categoria, preco);
    }

    public void atualizarProduto(){
        System.out.println("Digite o id do produto que deseja atualizar:");
        int id = Integer.parseInt(sc.nextLine());
        var produto = produtoService.buscarProdutoPorId(id);
        if(produto == null){
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Digite o novo nome do produto:");
        String nome = sc.nextLine();
        System.out.println("Digite a nova categoria do produto:");
        String categoria = sc.nextLine();
        System.out.println("Digite o novo preço do produto:");
        double preco = sc.nextDouble();

        produto.setNome(nome);
        produto.setCategoria(categoria);
        produto.setPreco(preco);

        produtoService.atualizarProduto(produto);
        System.out.println("Produto atualizado com sucesso!");
    }

    public void listarProdutos(){

        var produtos = produtoService.listarTodosProdutos();
        System.out.println("Lista de Produtos:");
        for(var produto : produtos){
            System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Categoria: " + produto.getCategoria() + ", Preço: " + produto.getPreco());
        }
    }

    public void deletarProduto(){
        System.out.println("Digite o id do produto que deseja deletar:");
        int id = sc.nextInt();
        var produto = produtoService.buscarProdutoPorId(id);
        if(produto == null){
            System.out.println("Produto não encontrado.");
            return;
        }
        produtoService.deletarProduto(id);
        System.out.println("Produto deletado com sucesso!");
    }

    public void buscarProdutoPorId(){
        System.out.println("Digite o id do produto que deseja buscar:");
        int id = Integer.parseInt(sc.nextLine());
        var produto = produtoService.buscarProdutoPorId(id);
        if(produto == null){
            System.out.println("Produto não encontrado.");
            return;
        }
        produtoToString(produto);
    }

    public List<Produto> buscarTodosProdutos(){
        var produtos = produtoService.listarTodosProdutos();
        for(var produto : produtos){
            produtoToString(produto);
        }
        return produtos;
    }

    public void produtoToString(Produto produto){
        System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Categoria: " + produto.getCategoria() + ", Preço: " + produto.getPreco());
    }
}
