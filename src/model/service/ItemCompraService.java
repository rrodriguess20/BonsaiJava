package model.service;

import model.dao.ItemCompraDAO;
import model.entities.ItemCompra;
import java.util.List;

public class ItemCompraService {
    
    private ItemCompraDAO itemCompraDAO;
  

    public ItemCompraService(ItemCompraDAO itemCompraDAO){
        this.itemCompraDAO = itemCompraDAO;   
    }

    public void adicionarItemCompra(int id_compra, ItemCompra itemCompra){
        itemCompra.setIdCompra(id_compra);
        itemCompraDAO.insert(itemCompra);
    }

    public void deletarItemCompra(ItemCompra itemCompra){
        itemCompraDAO.deleteById(itemCompra.getId());
    }

    public void editarItemCompra(ItemCompra itemCompra){
        itemCompraDAO.update(itemCompra);
    }

    public ItemCompra buscarItemCompraPorId(int id){
        return itemCompraDAO.findById(id);
    }
    public List<ItemCompra> listarTodosItensCompra(){
        return itemCompraDAO.findAll();
    }

}
