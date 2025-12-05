package model.entities;

public class Funcionario {

    private int id;
    private String cargo;
    private int id_usuario;

    
    public Funcionario(int id, String cargo, int id_usuario) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.cargo = cargo;
    }

     public Funcionario(String cargo, int id_usuario) {
        this.cargo = cargo;
        this.id_usuario = id_usuario;
     }

    public Funcionario(){

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

    public int getIdUsuario(){
        return id_usuario;
    }
    public void setIdUsuario(int id_usuario){
        this.id_usuario = id_usuario;
    }

}
