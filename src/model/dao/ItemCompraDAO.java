package model.dao;

import model.entities.ItemCompra;
import java.util.List;

public interface ItemCompraDAO {

    public void insert(ItemCompra itemCompra);
    public void update(ItemCompra itemCompra);
    public void deleteById(int id);
    public ItemCompra findById(int id);
    public List<ItemCompra> findAll();
}