package model.dao;

import db.DB;
import model.dao.impl.*;

public class DaoFactory {
    public static UsuarioDAO createUsuarioDAO() {
        return new UsuarioDAOJDBC(DB.getConnection());
    }

    public static FornecedorDAO createFornecedorDAO() {
        return new FornecedorDAOJDBC(DB.getConnection());
    }

    public static FuncionarioDAO createFuncionarioDAO(){
        return new FuncionarioDAOJDBC(DB.getConnection());
    }

    public static CompraDAO createCompraDAO() {
        return new CompraDAOJDBC(DB.getConnection());
    }

    public static VendaDAO createVendaDAO() {
        return new VendaDAOJDBC(DB.getConnection());
    }
}
