package model.entities;

import model.exceptions.InputFormatException;

public class Funcionario extends Pessoa {

    private String nome;
    private String telefone;
    private int id;
    private String cargo;
    private int id_usuario;
    private double salario;

    public Funcionario(String nome, String telefone, int id, String cargo, int id_usuario, double salario) throws InputFormatException {
        super(nome, telefone);

        if(id <= 0){
            throw new InputFormatException("Id inválido");
        }

        this.id = id;
        this.cargo = cargo;

        if(id_usuario <= 0){
            throw new InputFormatException("Id de Usuário inválido");
        }
        this.id_usuario = id_usuario;
        this.salario = salario;
    }

     public Funcionario(String cargo, int id_usuario) {
        this.cargo = cargo;

         if(id_usuario <= 0){
             throw new InputFormatException("Id de Usuário inválido");
         }
        this.id_usuario = id_usuario;
     }

    public Funcionario(){

    }

    //Overrides
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

    //Getters e setters

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

    public int getIdUsuario(){
        return id_usuario;
    }
    public void setIdUsuario(int id_usuario){
        this.id_usuario = id_usuario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
