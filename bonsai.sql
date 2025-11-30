CREATE TABLE Usuario (
  id_usuario SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  senha VARCHAR(100)
);

CREATE TABLE Funcionario (
  id_funcionario SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  cargo VARCHAR(50),
  usuario VARCHAR(50) UNIQUE,
  senha VARCHAR(100)
);

CREATE TABLE Fornecedor (
  id_fornecedor SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  cnpj VARCHAR(20),
  telefone VARCHAR(20)
);

CREATE TABLE Produto (
  id_produto SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  categoria VARCHAR(50),
  preco DECIMAL(10,2),
  quantidade_estoque INT,
  id_fornecedor INT,
  FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id_fornecedor)
);

CREATE TABLE Venda (
  id_venda SERIAL PRIMARY KEY,
  id_usuario INT,
  id_funcionario INT,
  data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  total DECIMAL(10,2),
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
  FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id_funcionario)
);

CREATE TABLE ItemVenda (
  id_item SERIAL PRIMARY KEY,
  id_venda INT,
  id_produto INT,
  quantidade INT,
  subtotal DECIMAL(10,2),
  FOREIGN KEY (id_venda) REFERENCES Venda(id_venda),
  FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);
