package model.dao;

import model.entities.Compra;
import java.util.List;

public interface CompraDAO {

   public void insert(Compra compra);
   public void update(Compra compra);
   public void deleteById(int id);
   public Compra findById(int id);
   public List<Compra> findAll();
   public List<Compra> findByFornecedorId(int id_fornecedor);
}