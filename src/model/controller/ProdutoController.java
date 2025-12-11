package model.controller;

import model.service.ProdutoService;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.entities.Produto;
import model.exceptions.EntityNotFoundException;
import model.exceptions.DatabaseException;

public class ProdutoController {

    private ProdutoService produtoService;

    private final Scanner sc = new Scanner(System.in);

    public ProdutoController(ProdutoService produtoService) {
    this.produtoService = produtoService;
    }

    public void cadastrarProduto(){
        try{
            System.out.println("Insira o nome do produto:");
            String nome = sc.nextLine();
            System.out.println("Insira a categoria do produto:");
            String categoria = sc.nextLine();
            System.out.println("Insira o preço do produto:");
            double preco = Double.parseDouble(sc.nextLine());

            produtoService.cadastrarProduto(nome, categoria, preco);
            System.out.println("Produto cadastrado com sucesso!");
        }catch(NumberFormatException e){
            System.out.println("Preço inválido. Operação cancelada.");
        }catch(DatabaseException e){
            System.out.println("Erro ao cadastrar produto!");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao cadastrar produto!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void atualizarProduto(){
        try{
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
            double preco = Double.parseDouble(sc.nextLine());

            produto.setNome(nome);
            produto.setCategoria(categoria);
            produto.setPreco(preco);

            produtoService.atualizarProduto(produto);
            System.out.println("Produto atualizado com sucesso!");
        }catch(NumberFormatException e){
            System.out.println("Valor numérico inválido. Operação cancelada.");
        }catch(EntityNotFoundException e){
            System.out.println("Produto não encontrado.");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(DatabaseException e){
            System.out.println("Erro ao atualizar produto! ");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao atualizar produto! ");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void listarProdutos(){
        try{
            var produtos = produtoService.listarTodosProdutos();
            if(produtos.isEmpty()){
                System.out.println("Nenhum produto cadastrado.");
                return;
            }
            System.out.println("Lista de Produtos:");
            for(var produto : produtos){
                produtoToString(produto);
            }
        }catch(DatabaseException e){
            System.out.println("Erro ao listar produtos!");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao listar produtos!");
            System.out.println("Detalhes: " + e.getMessage());
        }
    }

    public void deletarProduto(){
        try{
            System.out.println("Digite o id do produto que deseja deletar:");
            int id = Integer.parseInt(sc.nextLine());
            var produto = produtoService.buscarProdutoPorId(id);
            if(produto == null){
                System.out.println("Produto não encontrado.");
                return;
            }
            produtoService.deletarProduto(id);
            System.out.println("Produto deletado com sucesso!");
        }catch(NumberFormatException e){
            System.out.println("ID inválido. Operação cancelada.");
            return;
        }catch(EntityNotFoundException e){
            System.out.println("Produto não encontrado.");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }catch(DatabaseException e){
            System.out.println("Erro ao deletar produto!");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }catch(Exception e){
            System.out.println("Erro inesperado ao deletar produto!");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }
    }

    public void buscarProdutoPorId(){
        System.out.println("Digite o id do produto que deseja buscar:");
        Produto produto;
        try{
            int id = Integer.parseInt(sc.nextLine());
            produto = produtoService.buscarProdutoPorId(id);
        }catch(NumberFormatException e){
            System.out.println("ID inválido.");
            return;
        }catch(EntityNotFoundException e){
            System.out.println("Produto não encontrado.");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }catch(DatabaseException e){
            System.out.println("Erro ao buscar produto! ");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }catch(Exception e){
            System.out.println("Erro inesperado ao buscar produto! ");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }

        produtoToString(produto);
    }

    public List<Produto> buscarTodosProdutos(){
        try{
            var produtos = produtoService.listarTodosProdutos();
            for(var produto : produtos){
                produtoToString(produto);
            }
            return produtos;
        }catch(DatabaseException e){
            System.out.println("Erro ao buscar produtos! ");
            System.out.println("Detalhes: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Erro inesperado ao buscar produtos! ");
            System.out.println("Detalhes: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public void produtoToString(Produto produto){
        System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Categoria: " + produto.getCategoria() + ", Preço: " + produto.getPreco());
    }
}
