import application.Menu;
import db.DB;
import model.controller.CompraController;
import model.controller.FornecedorController;
import model.controller.FuncionarioController;
import model.controller.VendaController;
import model.dao.DaoFactory;
import model.service.CompraService;
import model.service.FornecedorService;
import model.service.FuncionarioService;
import model.service.UsuarioService;
import model.service.VendaService;

public class Main {

    public static void main(String[] args) {
        Menu menu = configurarAplicacao();
        try {
            menu.start();
        } catch (RuntimeException e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }
    }

    private static Menu configurarAplicacao() {
        UsuarioService usuarioService = new UsuarioService(DaoFactory.createUsuarioDAO());
        FuncionarioService funcionarioService = new FuncionarioService(DaoFactory.createFuncionarioDAO(), usuarioService);
        FornecedorService fornecedorService = new FornecedorService(DaoFactory.createFornecedorDAO());
        CompraService compraService = new CompraService(DaoFactory.createCompraDAO());
        VendaService vendaService = new VendaService(DaoFactory.createVendaDAO());

        FornecedorController fornecedorController = new FornecedorController(fornecedorService);
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioService, usuarioService);
        CompraController compraController = new CompraController(compraService);
        VendaController vendaController = new VendaController(vendaService, funcionarioService);

        return new Menu(fornecedorController, funcionarioController, compraController, vendaController);
    }
}