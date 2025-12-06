package model.service;

import model.dao.ItemVendaDAO;
import model.entities.ItemVenda;
import java.util.List;

public class ItemVendaService {

    private ItemVendaDAO itemVendaDAO;

    public ItemVendaService(ItemVendaDAO itemVentaDAO){
        this.itemVendaDAO = itemVentaDAO;
    }

    public void adicionarItemVenda(int id_venda, ItemVenda itemVenda){
        itemVenda.setId(id_venda);
        itemVendaDAO.insert(itemVenda);
    }
    public void deletarItemVenda(ItemVenda itemVenda){
        itemVendaDAO.deleteById(itemVenda.getId());
    }
    public void editarItemVenda(ItemVenda itemVenda){
        itemVendaDAO.update(itemVenda);
    }
    public ItemVenda buscarItemVendaPorId(int id){
        return itemVendaDAO.findById(id);
    }
    public List<ItemVenda> listarTodosItensVenda(){
        return itemVendaDAO.findAll();
    }

}
