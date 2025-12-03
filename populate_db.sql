-- USUÁRIOS
INSERT INTO Usuario (nome, email, senha)
VALUES
    ('Clara Santos', 'clara.santos@bonsai.com', '123456'),
    ('Marcos Oliveira', 'marcos.oliveira@bonsai.com', 'abc123'),
    ('Juliana Costa', 'juliana.costa@bonsai.com', 'senhaSegura!');

-- FUNCIONÁRIOS
INSERT INTO Funcionario (cargo, id_usuario) VALUES
    ('Delivery', 1),
    ('Delivery', 2),
    ('Entrgador', 3);

-- FORNECEDORES
INSERT INTO Fornecedor (nome, cnpj, telefone) VALUES
    ('Distribuidora Vida Saudável', '12.345.678/0001-90', '(85) 98888-1111'),
    ('Fazenda Bela Vista', '23.456.789/0001-80', '(85) 97777-2222'),
    ('Limpex Produtos de Limpeza', '34.567.890/0001-70', '(85) 96666-3333'),
    ('HigiePlus', '45.678.901/0001-60', '(85) 95555-4444');

-- PRODUTOS
INSERT INTO Produto (nome, categoria, preco, quantidade_estoque, id_fornecedor) VALUES
    ('Arroz Branco 5kg', 'Cereais', 24.90, 80, 1),
    ('Feijão Carioca 1kg', 'Cereais', 8.50, 100, 1),
    ('Ovos Brancos (Dúzia)', 'Proteína', 12.00, 60, 2),
    ('Peito de Frango 1kg', 'Proteína', 18.90, 50, 2),
    ('Leite Integral 1L', 'Laticínios', 4.80, 70, 1),
    ('Queijo Mussarela 500g', 'Laticínios', 21.50, 30, 1),
    ('Maçã Nacional 1kg', 'Hortifruti', 7.90, 40, 2),
    ('Banana Prata 1kg', 'Hortifruti', 6.50, 40, 2),
    ('Detergente Neutro 500ml', 'Limpeza', 3.20, 120, 3),
    ('Sabão em Pó 1kg', 'Limpeza', 11.90, 80, 3),
    ('Sabonete Neutro 90g', 'Higiene', 2.50, 150, 4),
    ('Pasta de Dente 90g', 'Higiene', 5.80, 100, 4);

-- VENDAS
INSERT INTO Venda (id_usuario, id_funcionario, total) VALUES

    (3, 1, 48.10),
    (1, 2, 32.70);

-- ITENS DAS VENDAS
INSERT INTO ItemVenda (id_venda, id_produto, quantidade, subtotal) VALUES
    (1, 1, 1, 24.90),   -- arroz
    (1, 3, 1, 12.00),   -- ovos
    (1, 9, 2, 6.40),    -- detergente
    (2, 2, 2, 17.00),   -- feijão
    (2, 11, 2, 5.00),   -- sabonete
    (2, 12, 1, 5.80);   -- pasta de dente