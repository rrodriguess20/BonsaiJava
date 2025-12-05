CREATE TABLE Usuario (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  senha VARCHAR(100)
);

CREATE TABLE Funcionario (
  id SERIAL PRIMARY KEY,
  id_usuario INT,
  cargo VARCHAR(50),
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Fornecedor (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  cnpj VARCHAR(20),
  telefone VARCHAR(20)
);

CREATE TABLE Produto (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  categoria VARCHAR(50),
  preco DECIMAL(10,2)
);

CREATE TABLE Venda (
  id SERIAL PRIMARY KEY,
  id_funcionario INT,
  data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  total DECIMAL(10,2),
  FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id)
);

CREATE TABLE ItemVenda (
  id SERIAL PRIMARY KEY,
  id_venda INT,
  id_produto INT,
  quantidade INT,
  preco_unitario DECIMAL(10,2),
  subtotal DECIMAL(10,2),
  FOREIGN KEY (id_venda) REFERENCES Venda(id),
  FOREIGN KEY (id_produto) REFERENCES Produto(id)
);

CREATE TABLE Compra (
  id SERIAL PRIMARY KEY,
  id_fornecedor INT,
  data_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  valor_total DECIMAL(10,2),
  FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id)
);

CREATE TABLE ItemCompra (
  id SERIAL PRIMARY KEY,
  id_compra INT,
  id_produto INT,
  quantidade INT,
  preco_unitario DECIMAL(10,2),
  subtotal DECIMAL(10,2),
  FOREIGN KEY (id_compra) REFERENCES Compra(id),
  FOREIGN KEY (id_produto) REFERENCES Produto(id)
);

CREATE TABLE MovimentacaoEstoque (
  id SERIAL PRIMARY KEY,
  id_produto INT,
  quantidade INT,
  tipo_movimentacao VARCHAR(50) CHECK (tipo_movimentacao IN ('entrada', 'saida')),
  data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_produto) REFERENCES Produto(id)
);
