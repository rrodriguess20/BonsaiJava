

 package model.entities;

import java.time.LocalDate;

public class Compra {

    private int id;
    private int id_fornecedor;
    private LocalDate data_compra;
    private double valor_total;

    public Compra() {
    }

    public Compra(int id, int id_fornecedor, LocalDate data_compra, double valor_total) {
        this.id = id;
        this.id_fornecedor = id_fornecedor;
        this.data_compra = data_compra;
        this.valor_total = valor_total;
    }

    public Compra(int id_fornecedor, LocalDate data_compra, double valor_total) {
        this.id_fornecedor = id_fornecedor;
        this.data_compra = data_compra;
        this.valor_total = valor_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFornecedor() {
        return id_fornecedor;
    }

    public void setIdFornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public LocalDate getDataCompra() {
        return data_compra;
    }

    public void setDataCompra(LocalDate data_compra) {
        this.data_compra = data_compra;
    }

    public double getValorTotal() {
        return valor_total;
    }

    public void setValorTotal(double valor_total) {
        this.valor_total = valor_total;
    }
}