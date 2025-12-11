import application.Menu;
import db.DB;
import model.controller.CompraController;
import model.controller.FornecedorController;
import model.controller.FuncionarioController;
import model.controller.VendaController;
import model.controller.ItemCompraController;
import model.controller.ProdutoController;
import model.dao.DaoFactory;
import model.service.CompraService;
import model.service.FornecedorService;
import model.service.FuncionarioService;
import model.service.ItemCompraService;
import model.service.UsuarioService;
import model.service.VendaService;
import model.service.ProdutoService;
import model.entities.ItemCompra;
import model.entities.Produto;

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
        ItemCompraService itemCompraService = new ItemCompraService(DaoFactory.createItemCompraDAO());
        ProdutoService produtoService = new ProdutoService(DaoFactory.createProdutoDAO());

        FornecedorController fornecedorController = new FornecedorController(fornecedorService);
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioService, usuarioService);
        ItemCompraController itemCompraController = new ItemCompraController(itemCompraService, compraService);
        CompraController compraController = new CompraController(compraService, itemCompraController);
        VendaController vendaController = new VendaController(vendaService, funcionarioService);
        ProdutoController produtoController = new ProdutoController(produtoService);


        return new Menu(fornecedorController, funcionarioController, compraController, vendaController, itemCompraController, produtoController);
    }
}