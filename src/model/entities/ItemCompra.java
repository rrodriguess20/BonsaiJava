package model.entities;

public class ItemCompra {

    private int id;
    private int id_compra;
    private int id_produto;
    private int quantidade;
    private double preco_unitario;

    public ItemCompra(int id, int id_compra, int id_produto, int quantidade, double preco_unitario) {
        this.id = id;
        this.id_compra = id_compra;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    public ItemCompra(int id_compra, int id_produto, int quantidade, double preco_unitario) {
        this.id_compra = id_compra;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    public ItemCompra() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompra() {
        return id_compra;
    }

    public void setIdCompra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getIdProduto() {
        return id_produto;
    }

    public void setIdProduto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return preco_unitario;
    }

    public void setPrecoUnitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}