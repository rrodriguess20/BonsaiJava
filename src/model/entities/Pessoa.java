package model.entities;

import model.exceptions.InputFormatException;

public abstract class Pessoa {

    private String nome;
    private String telefone;

    public Pessoa(String nome, String telefone) {

        if(nome == null || nome.isBlank()) {
            throw new InputFormatException("Nome inválido");
        }
        if(telefone == null || telefone.isBlank()) {
            throw new InputFormatException("Telefone inválido");
        }
        this.nome = nome;
        this.telefone = telefone;
    }

    public Pessoa(){

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
}
