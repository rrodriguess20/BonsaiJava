package model.service;

import model.dao.VendaDAO;
import model.entities.Venda;
import java.util.List;

public class VendaService {

    private VendaDAO vendaDAO;
    
    public VendaService(VendaDAO vendaDAO){
        this.vendaDAO = vendaDAO;   
    }

    public void adicionarVenda(Venda venda){
        vendaDAO.insert(venda);
    }

    public void deletarVenda(Venda venda){
        vendaDAO.deleteById(venda.getId());
    }

    public void editarVenda(Venda venda){
        vendaDAO.update(venda);
    }

    public Venda buscarVendaPorId(int id){
        return vendaDAO.findById(id);
    }

    public List<Venda> listarTodasVendas(){
        return vendaDAO.findAll();
    }

}
