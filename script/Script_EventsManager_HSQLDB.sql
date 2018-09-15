CREATE CACHED TABLE tb_usuario (
  id INT NOT NULL IDENTITY,
  nome VARCHAR(255) NOT NULL,
  apelido VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  dtCadastro DATE NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE CACHED TABLE tb_endereco (
  id INT NOT NULL IDENTITY,
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

CREATE CACHED TABLE tb_amigo (
	id INT NOT NULL IDENTITY,
    idUsuario INT NOT NULL,
    idAmigo INT NOT NULL,
    CONSTRAINT pk_amigo PRIMARY KEY (id),
    CONSTRAINT fk_usuario_usuario FOREIGN KEY (idUsuario) REFERENCES tb_usuario (id),
	CONSTRAINT fk_amigo_usuario FOREIGN KEY (idAmigo) REFERENCES tb_usuario (id)
);
