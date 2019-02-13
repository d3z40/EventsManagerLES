DROP DATABASE IF EXISTS LES;
CREATE DATABASE LES;
USE LES;

CREATE TABLE tb_usuario (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  apelido VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  dtCadastro DATE NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE tb_endereco (
  id INT NOT NULL AUTO_INCREMENT,
  idUsuario INT NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  numero INT  NOT NULL,
  bairro VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  estado VARCHAR(255) NOT NULL,
  cep VARCHAR(8) NOT NULL,
  dtCadastro DATE NOT NULL,
  CONSTRAINT pk_endereco PRIMARY KEY (id),
  CONSTRAINT fk_usuario FOREIGN KEY (idUsuario) REFERENCES tb_usuario (id)
);

CREATE TABLE tb_amigo (
	id INT NOT NULL AUTO_INCREMENT,
    idUsuario INT NOT NULL,
    idAmigo INT NOT NULL,
    CONSTRAINT pk_amigo PRIMARY KEY (id),
    CONSTRAINT fk_usuario_usuario FOREIGN KEY (idUsuario) REFERENCES tb_usuario (id),
	CONSTRAINT fk_amigo_usuario FOREIGN KEY (idAmigo) REFERENCES tb_usuario (id)
);

CREATE TABLE tb_evento (
  id INT NOT NULL AUTO_INCREMENT,
  IdEndereco INT NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  dtEvento DATE NOT NULL,
  dtCadastro DATE NOT NULL,
  CONSTRAINT pk_evento PRIMARY KEY (id)
  CONSTRAINT fk_evento_endereco FOREIGN KEY (IdEndereco) REFERENCES tb_endereco (id)
);

CREATE TABLE tb_item (
  id INT NOT NULL AUTO_INCREMENT,
  idEvento INT NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  dtCadastro DATE NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id)
  CONSTRAINT fk_item_evento FOREIGN KEY (IdEvento) REFERENCES tb_evento (id)
);
