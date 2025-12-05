package model.service;

import model.dao.MovimentacaoEstoqueDAO;
import model.entities.MovimentacaoEstoque;
import java.util.List;

public class MovimentacaoEstoqueService {

    private MovimentacaoEstoqueDAO movimentacaoEstoqueDAO;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueDAO movimentacaoEstoqueDAO){
        this.movimentacaoEstoqueDAO = movimentacaoEstoqueDAO;   
    }

    public void adicionarMovimentacaoEstoque(MovimentacaoEstoque movimentacao){
        movimentacaoEstoqueDAO.insert(movimentacao);
    }

    public void deletarMovimentacaoEstoque(MovimentacaoEstoque movimentacao){
        movimentacaoEstoqueDAO.deleteById(movimentacao.getId());
    }

    public void editarMovimentacaoEstoque(MovimentacaoEstoque movimentacao){
        movimentacaoEstoqueDAO.update(movimentacao);
    }

    public MovimentacaoEstoque buscarMovimentacaoEstoquePorId(int id){
        return movimentacaoEstoqueDAO.findById(id);
    }

    public List<MovimentacaoEstoque> listarTodasMovimentacoesEstoque(){
        return movimentacaoEstoqueDAO.findAll();
    }



}
