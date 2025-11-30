package model.entities;

import java.time.LocalDateTime;

public class Venda {
    private int id;
    private Usuario usuario;
    private Funcionario funcionario;
    private LocalDateTime dataVenda;
    private int quantidade;
    private double valorTotal;

    public Venda(int id, Usuario usuario, int quantidade, double valorTotal) {
        this.id = id;
        this.usuario = usuario;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
}
