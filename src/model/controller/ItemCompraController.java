package model.controller;

import model.service.ItemCompraService;
import model.entities.ItemCompra;
import model.service.CompraService;


public class ItemCompraController {
    

    private ItemCompraService itemCompraService;
    private CompraService compraService;


    private final java.util.Scanner sc = new java.util.Scanner(System.in);

    public ItemCompraController(ItemCompraService itemCompraService, CompraService compraService) {
        this.itemCompraService = itemCompraService;
        this.compraService = compraService;
    }

    public void itensComprasMenu(int id_compra) {
		boolean back = false;
		while (!back) {
			System.out.println("1 - Adicionar item à compra");
			System.out.println("2 - Remover item da compra");
			System.out.println("3 - Listar itens da compra");
			System.out.println("0 - Voltar");
			int option = sc.nextInt();
			switch (option) {
				case 1 -> adicionarItemCompra(id_compra);
				case 2 -> deletarItemCompra(id_compra);
				case 3 -> listarTodosItensCompra(id_compra);
				case 0 -> back = true;
				default -> System.out.println("Opção inválida!");
			}
		}
	}

    public void adicionarItemCompra(int id_compra) {
        System.out.println("Digite o id do Produto:");
        int id_produto = sc.nextInt();
        System.out.println("Digite a quantidade do Produto:");
        int quantidade = sc.nextInt();
        System.out.println("Digite o preço unitário do Produto:");
        double preco_unitario = sc.nextDouble();
        ItemCompra itemCompra = new ItemCompra();
        itemCompra.setIdProduto(id_produto);
        itemCompra.setQuantidade(quantidade);
        itemCompra.setPrecoUnitario(preco_unitario);

        itemCompraService.adicionarItemCompra(id_compra, itemCompra);

    }

    public void deletarItemCompra(int id_compra) {
        System.out.println("Digite o id do item que deseja deletar:");
        int id = sc.nextInt();
        var itemCompra = itemCompraService.buscarItemCompraPorId(id);
        if (itemCompra == null || itemCompra.getIdCompra() != id_compra) {
            System.out.println("Item de compra não encontrado para esta compra.");
            return;
        }
        itemCompraService.deletarItemCompra(itemCompra);
        System.out.println("Item de compra deletado com sucesso!");
       
    }

    public void editarItemCompra(int id_compra) {
        System.out.println("Digite o id do item que deseja editar:");
        int id = sc.nextInt();
        var itemCompra = itemCompraService.buscarItemCompraPorId(id);
        if (itemCompra == null || itemCompra.getIdCompra() != id_compra) {
            System.out.println("Item de compra não encontrado para esta compra.");
            return;
        }
        System.out.println("Digite a nova quantidade do Produto:");
        int quantidade = sc.nextInt();
        System.out.println("Digite o novo preço unitário do Produto:");
        double preco_unitario = sc.nextDouble();
        itemCompra.setQuantidade(quantidade);
        itemCompra.setPrecoUnitario(preco_unitario);

        itemCompraService.editarItemCompra(itemCompra);
        System.out.println("Item de compra atualizado com sucesso!");
    }

    public ItemCompra buscarItemCompraPorId() {
        System.out.println("Digite o id do item que deseja buscar:");
        int id = sc.nextInt();
        var itemCompra = itemCompraService.buscarItemCompraPorId(id);
        if (itemCompra == null) {
            System.out.println("Item de compra não encontrado.");
            return null;
        }
        return itemCompra;
    }

    public java.util.List<ItemCompra> listarTodosItensCompra(int id_compra) {
        var itensCompra = itemCompraService.listarTodosItensCompra();
        for (var item : itensCompra) {
            if (item.getIdCompra() == id_compra) {
                itemCompraToString(item);
            }
        }
        return itensCompra;
    }

    public void itemCompraToString(ItemCompra itemCompra) {
        System.out.println("ID do Item: " + itemCompra.getId());
        System.out.println("ID da Compra: " + itemCompra.getIdCompra());
        System.out.println("ID do Produto: " + itemCompra.getIdProduto());
        System.out.println("Quantidade: " + itemCompra.getQuantidade());
        System.out.println("Preço Unitário: " + itemCompra.getPrecoUnitario());
    }
}
