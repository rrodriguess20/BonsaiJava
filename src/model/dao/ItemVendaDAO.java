package model.dao;

import model.entities.ItemVenda;
import java.util.List;

public interface ItemVendaDAO {

    void insert(ItemVenda itemVenda);
    ItemVenda findById(int id);
    List<ItemVenda> findAll();
    void update(ItemVenda itemVenda);
    void deleteById(int id);

}
