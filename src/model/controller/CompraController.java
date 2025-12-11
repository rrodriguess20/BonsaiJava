package model.controller;

import java.time.LocalDate;
import java.util.Scanner;

import model.entities.Compra;
import model.service.CompraService;


public class CompraController {

    private  CompraService compraService;
    private ItemCompraController itemCompraController;

    private final Scanner sc = new Scanner(System.in);

    public CompraController(CompraService compraService, ItemCompraController itemCompraController) {
        this.compraService = compraService;
        this.itemCompraController = itemCompraController;
    }

    public void cadastrarCompra(){

        System.out.println("Insira o id do fornecedor:");
        int id_fornecedor = sc.nextInt();

        //Valores automáticos
        LocalDate data_compra = LocalDate.now();

        //Criar objeto Compra
        Compra compra = new Compra();
        compra.setIdFornecedor(id_fornecedor);
        compra.setDataCompra(data_compra);

        compraService.cadastrarCompra(compra);

        itemCompraController.itensComprasMenu(compra.getId());

    }

    public void atualizarCompra(){
        System.out.println("Digite o id da compra que  deseja atualizar:");
        int id = Integer.parseInt(sc.nextLine());
        var compra = compraService.buscarCompraPorId(id);
        if(compra == null){
            System.out.println("Compra não encontrada.");
            return;
        }

        System.out.println("Digite o valor total da compra:");
        double valor_total = sc.nextDouble();
        compra.setValorTotal(valor_total);
        compraService.atualizarCompra(compra);
        System.out.println("Compra atualizada com sucesso!");
    }

    public void listarCompra(){

        var compras = compraService.listarTodasCompras();
        for(var comp : compras){
            System.out.println(comp);
        }
    }

    public void deletarCompra(){
        System.out.println("Digite o id da compra que deseja deletar:");
        int id = Integer.parseInt(sc.nextLine());
        compraService.deletarCompra(id);
        System.out.println("Compra deletada com sucesso!");
    }

    public void buscarCompraPorId(){
        System.out.println("Digite o id da compra que deseja buscar:");
        int id = Integer.parseInt(sc.nextLine());
        var compra = compraService.buscarCompraPorId(id);
        if(compra == null){
            System.out.println("Compra não encontrada.");
            return;
        }
        System.out.println(compra);
    }
}
