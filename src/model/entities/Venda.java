package model.entities;

import java.time.LocalDateTime;

public class Venda {
    private int id;
    private Funcionario funcionario;
    private LocalDateTime dataVenda;
    private double total;

    public Venda(int id, Funcionario funcionario, LocalDateTime dataVenda, double total) {
        this.id = id;
        this.funcionario = funcionario;
        this.dataVenda = dataVenda;
        this.total = total;
    }

    public Venda(Funcionario funcionario, LocalDateTime dataVenda, double total) {
        this.funcionario = funcionario;
        this.dataVenda = dataVenda;
        this.total = total;
    }

    public Venda() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
