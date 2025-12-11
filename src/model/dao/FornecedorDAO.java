package model.dao;

import model.entities.Fornecedor;
import java.util.List;

public interface FornecedorDAO {
    void insert(Fornecedor fornecedor) ;
    void update(Fornecedor fornecedor);
    void deleteById(int id);
    Fornecedor findById(int id);
    Fornecedor findByCnpj(String cnpj);
    List<Fornecedor> findByName(String nome);
    List<Fornecedor> findAll();
}
