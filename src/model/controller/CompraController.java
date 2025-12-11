package model.controller;

import java.time.LocalDate;
import java.util.Scanner;

import model.entities.Compra;
import model.exceptions.DatabaseException;
import model.exceptions.EntityNotFoundException;
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
        int id;
        try{
            id = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("ID inválido.");
            return;
        }
        final Compra compra;
        
        compra = compraService.buscarCompraPorId(id);
        
        try{
            compraService.deletarCompra(compra.getId());
        }catch (EntityNotFoundException e){
            System.out.println("Compra não encontrada.");
            System.out.println("Detalhes: " + e.getMessage());
            return;
        }catch(DatabaseException e){
            System.out.println("Erro ao buscar compra: " + e.getMessage());
            return;
        }
        catch(Exception e){
            System.out.println("Erro inesperado: " + e.getMessage());
            return;
        }
        System.out.println("Compra deletada com sucesso!");
    }

    public void buscarCompraPorId(){
        System.out.println("Digite o id da compra que deseja buscar:");
        int id;
        Compra compra;
        try{
        id = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("ID inválido.");
            return;
        }
        try{
            compra = compraService.buscarCompraPorId(id);
        }catch(DatabaseException e){
            System.out.println("Erro ao buscar compra: " + e.getMessage());
            return;
        }catch(EntityNotFoundException e){
            System.out.println("Compra não encontrada: " + e.getMessage());
            return;
        }
        catch(Exception e){
            System.out.println("Erro inesperado: " + e.getMessage());
            return;
        }
        compraToString(compra);
    }

    public void listarTodasCompras(){
        var compras = compraService.listarTodasCompras();
        for(var compra : compras){
            compraToString(compra);
            System.out.println("-------------------");
        }
    }

    public void compraToString(Compra compra){
        System.out.println("ID: " + compra.getId());
        System.out.println("ID Fornecedor: " + compra.getIdFornecedor());
        System.out.println("Data da Compra: " + compra.getDataCompra());
        System.out.println("Valor Total: " + compra.getValorTotal());
    }
}
