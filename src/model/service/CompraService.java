package model.service;

import model.dao.CompraDAO;
import model.entities.Compra;
import java.util.List;

public class CompraService {

    private CompraDAO compraDAO;

    public CompraService(CompraDAO compraDAO){
        this.compraDAO = compraDAO;
    }

    public void cadastrarCompra(Compra compra){
        compraDAO.insert(compra);
    }

    public void atualizarCompra(Compra compra){
        compraDAO.update(compra);
    }

    public void deletarCompra(int id){
        compraDAO.deleteById(id);
    }

    public Compra buscarCompraPorId(int id){
        return compraDAO.findById(id);
    }

    public List<Compra> listarTodasCompras(){
        return compraDAO.findAll();
    }

    public List<Compra> listarComprasPorFornecedorId(int id_fornecedor){
        return compraDAO.findByFornecedorId(id_fornecedor);
    }



    
}