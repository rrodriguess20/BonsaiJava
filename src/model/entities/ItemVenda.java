package model.entities;

public class ItemVenda {
    private int id;
    private int vendaId;
    private int produtoId;
    private int quantidade;
    private double precoUnitario;
    private double subtotal;

    public ItemVenda(int id_item_venda, int id_venda, int id_produto, int quantidade, double preco_unitario) {
        this.id_item_venda = id_item_venda;
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    public ItemVenda(int id_venda, int id_produto, int quantidade, double preco_unitario) {
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    public ItemVenda() {
        
    }

    public int getId_item_venda() {
        return id_item_venda;
    }

    public void setId_item_venda(int id_item_venda) {
        this.id_item_venda = id_item_venda;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}
