package model.dao;

import model.entities.Produto;
import java.util.List;

public interface ProdutoDAO {
    void insert(Produto produto);
    void update(Produto produto);
    void deleteById(int id);
    Produto findById(int id);
    List<Produto> findAll();
}
