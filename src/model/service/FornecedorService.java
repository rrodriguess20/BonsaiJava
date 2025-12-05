package model.service;

import model.dao.FornecedorDAO;
import model.entities.Fornecedor;
import java.util.List;

public class FornecedorService {
    
    public FornecedorDAO fornecedorDAO;

    public FornecedorService(FornecedorDAO fornecedorDAO){
        this.fornecedorDAO = fornecedorDAO;
    }

    public void cadastrarFornecedor(Fornecedor fornecedor){
        fornecedorDAO.insert(fornecedor);
    }

    public void atualizarFornecedor(Fornecedor fornecedor){
        fornecedorDAO.update(fornecedor);
    }

    public void deletarFornecedor(int id){
        fornecedorDAO.deleteById(id);
    }

    public Fornecedor buscarFornecedorPorId(int id){
        return fornecedorDAO.findById(id);
    }

    public List<Fornecedor> listarTodosFornecedores(){
        return fornecedorDAO.findAll();
    }

    public Fornecedor buscarFornecedorPorCnpj(String cnpj){
        return fornecedorDAO.findByCnpj(cnpj);
    }

    public List<Fornecedor> buscarFornecedoresPorNome(String nome){
        return fornecedorDAO.findByName(nome);
    }
    
}
