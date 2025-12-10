package model.entities;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {

    private int id;
    private int id_produto;
    private int quantidade;
    private String tipo_movimentacao;
    private LocalDateTime data_movimentacao;

    public MovimentacaoEstoque() {

    }

    public MovimentacaoEstoque(int id, int id_produto, int quantidade, String tipo_movimentacao, LocalDateTime data_movimentacao) {
        this.id = id;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.tipo_movimentacao = tipo_movimentacao;
        this.data_movimentacao = data_movimentacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTipoMovimentacao() {
        return tipo_movimentacao;
    }

    public void setTipoMovimentacao(String tipo_movimentacao) {
        this.tipo_movimentacao = tipo_movimentacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return data_movimentacao;
    }

    public void setDataMovimentacao(LocalDateTime data_movimentacao) {
        this.data_movimentacao = data_movimentacao;
    }
}