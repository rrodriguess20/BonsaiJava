package model.dao;

import model.entities.Venda;
import java.util.List;


public interface VendaDAO {
    void novaVenda(Venda venda);
    void editarVenda(Venda venda);
    void deletarVendaPorId(int id);
    Venda procurarVenda(int id);
    List<Venda> listarTodas();
}
