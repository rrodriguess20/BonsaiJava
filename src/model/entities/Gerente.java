package model.entities;

public class Gerente extends Funcionario {

    private String nome;
    private String telefone;
    private int id;
    private String cargo;
    private int id_usuario;
    private double salario;

    public Gerente(String nome, String telefone, int id, String cargo, int id_usuario) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
        this.cargo = cargo;
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public double getSalario() {

        return salario*0.2;
    }
}
