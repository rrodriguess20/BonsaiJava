package model.dao;

import model.entities.MovimentacaoEstoque;
import java.util.List;

public interface MovimentacaoEstoqueDAO {

    public void insert(MovimentacaoEstoque movimentacao);
    public void update(MovimentacaoEstoque movimentacao);
    public void deleteById(int id);
    public MovimentacaoEstoque findById(int id);
    public List<MovimentacaoEstoque> findAll();      
}