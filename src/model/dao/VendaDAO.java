package model.dao;

import model.entities.Venda;
import java.util.List;


public interface VendaDAO {
    void insert(Venda venda);
    void update(Venda venda);
    void deleteById(int id);
    Venda findById(int id);
    List<Venda> findAll();
}
