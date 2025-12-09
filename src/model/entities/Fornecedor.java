package model.entities;

public class Fornecedor extends Pessoa {
    private int id;
    private String nome;
    private String cnpj;
    private String telefone;

    public Fornecedor(int id, String nome, String cnpj, String telefone) {
        super(nome, telefone);
        this.id = id;
        this.cnpj = cnpj;
    }

    public Fornecedor(String nome, String telefone, String cnpj){
        super(nome, telefone);
        this.cnpj = cnpj;
    }

    public Fornecedor(){

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

    //Getters e setters da classe

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    }

