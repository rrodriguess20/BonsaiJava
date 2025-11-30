package model.dao;

import model.entities.Funcionario;
import java.util.List;

public interface FuncionarioDAO {
    void insert(Funcionario funcionario);
    void update(Funcionario funcionario);
    void deleteById(int id);
    Funcionario findById(int id);
    List<Funcionario> findAll();
}
