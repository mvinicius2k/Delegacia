-- tabela UF
CREATE TABLE IF NOT EXISTS UF
(codUF serial not null,
 nomeUF varchar (50) not null,

 CONSTRAINT ufPK PRIMARY KEY (codUF)
 );


-- tabela cidade
CREATE TABLE IF NOT EXISTS Cidade
(codCidade serial not null,
 nomeCidade varchar (60) not null,
 idUF integer not null,

 CONSTRAINT cidadePK PRIMARY Key (codCidade),
 CONSTRAINT ufFK FOREIGN KEY (idUF) REFERENCES UF (codUF)
);

-- tabela bairro
CREATE TABLE IF NOT EXISTS Bairro
(codBairro serial not null,
 nomeBairro varchar (60) not null,
 idCidade integer not null,

 CONSTRAINT bairroPK PRIMARY Key (codBairro),
 CONSTRAINT cidadeFK FOREIGN KEY (idCidade) REFERENCES Cidade (codCidade)
);

-- tabela rua
CREATE TABLE IF NOT EXISTS Rua
(codRua serial not null,
 nomeRua varchar (80) not null,
 idBairro integer not null,

 CONSTRAINT ruaPK PRIMARY Key (codRua),
 CONSTRAINT bairroFK FOREIGN KEY (idBairro) REFERENCES Bairro (codBairro)
);

-- tabela endereço
CREATE TABLE IF NOT EXISTS Endereco
(codEndereco serial not null,
 idRua integer not null,
 idBairro integer not null,
 idCiade integer not null,
 idUF integer not null,
 numero integer not null,
 complemento varchar (50),


 CONSTRAINT enderecoPK PRIMARY Key (codEndereco), -- PK

 -- FK
 CONSTRAINT ruaFK FOREIGN KEY (idRua) REFERENCES Rua (codRua),
 CONSTRAINT bairroFK FOREIGN KEY (idBairro) REFERENCES Bairro (codBairro),
 CONSTRAINT cidadeFK FOREIGN KEY (idCidade) REFERENCES Cidade (codCidade),
 CONSTRAINT ufFK FOREIGN KEY (idUF) REFERENCES UF (codUF)
 -- FK
);

-- tabela pessoa
CREATE TABLE IF NOT EXISTS Pessoa
(codPessoa serial not null,
 nomePessoa varchar (80) not null,
 cpf varchar (14),
 dataNasc date,
 contato integer, 
 idEndereco integer not null,

 CONSTRAINT pessoaPK PRIMARY Key (codPessoa), -- PK
  CONSTRAINT pessoaUnique UNIQUE (cpf), -- Unique
 CONSTRAINT enderecoFK FOREIGN KEY (idEndereco) REFERENCES Endereco (codEndereco) -- FK
);

-- tabela contato
CREATE TABLE IF NOT EXISTS Contato
(codContato serial not null,
 idPessoa integer not null,
 contato varchar (80) not null,

 CONSTRAINT contatoPK PRIMARY Key (codContato), -- PK
 CONSTRAINT pessoaFK FOREIGN KEY (idPessoa) REFERENCES Pessoa (codPessoa) -- FK
);

-- tabela vitima 
CREATE TABLE IF NOT EXISTS Vitima
(codVitima serial not null,
 idPessoa integer not null,
 estadoCorpo varchar (100) not null,

 CONSTRAINT vitimaPK PRIMARY Key (codVitima), -- PK
 CONSTRAINT pessoaFK FOREIGN KEY (idPessoa) REFERENCES Pessoa (codPessoa) -- FK	 
);

-- tabela vitima 
CREATE TABLE IF NOT EXISTS Criminoso
(codCriminoso serial not null,
 idPessoa integer not null,
 escolaridade varchar (50) not null,

 CONSTRAINT criminosoPK PRIMARY Key (codCriminoso), -- PK
 CONSTRAINT pessoaFK FOREIGN KEY (idPessoa) REFERENCES Pessoa (codPessoa) -- FK	 
);

-- tabela lei 
CREATE TABLE IF NOT EXISTS Lei
(codLei serial not null,
 idLei integer not null,
 descricao varchar (400) not null,

 CONSTRAINT leiPK PRIMARY Key (codLei), -- PK
 CONSTRAINT leiUnique UNIQUE (idLei) -- Unique
);

-- tabela arma
CREATE TABLE IF NOT EXISTS Criminoso
(codArma serial not null,
 nome varchar (50) not null,
 descricao varchar (400) not null,

 CONSTRAINT armaPK PRIMARY Key (codArma) -- PK
 );


-- tabela crime
CREATE TABLE IF NOT EXISTS Crime
(codCrime serial not null,
 idEndereco integer not null,
 dataOcorrencia varchar (70) not null,
 dataComunicacao timestamp not null,
 fragrante boolean not null,
 consumado boolean not null,
 descricao varchar(1000) not null,

 CONSTRAINT crimePK PRIMARY Key (codCrime), -- PK
 -- FK	 
 CONSTRAINT enderecoFK FOREIGN KEY (idEndereco) REFERENCES Endereco (codEndereco) -- FK	 
);

-- relacionamento de crime com vitima
CREATE TABLE IF NOT EXISTS CrimeVitima
(idVitima integer not null,
 idCrime integer not null,

 CONSTRAINT crimeVitimaPK PRIMARY KEY (idVitima, idCrime), -- PK
 CONSTRAINT vitimaFK FOREIGN KEY (idVitima) REFERENCES Vitima (codVitima), -- FK
 CONSTRAINT crimeFK FOREIGN KEY (idCrime) REFERENCES Crime (codCrime) -- FK
);

-- relacionamento de crime com criminoso
CREATE TABLE IF NOT EXISTS CrimeCrimonoso
(idCriminoso integer not null,
 idCrime integer not null,

 CONSTRAINT crimeCriminosoPK PRIMARY KEY (idCriminoso, idCrime), -- PK
 CONSTRAINT crimeFK FOREIGN KEY (idCrime) REFERENCES Crime (codCrime), -- FK
 CONSTRAINT criminosoFK FOREIGN KEY (idCriminoso) REFERENCES Criminoso (codCriminoso) -- FK
);


-- relacionamento de crime com lei
CREATE TABLE IF NOT EXISTS CrimeLei
(idLei integer not null,
 idCrime integer not null,

 CONSTRAINT crimeLeiPK PRIMARY KEY (idLei, idCrime), -- PK
 CONSTRAINT crimeFK FOREIGN KEY (idCrime) REFERENCES Crime (codCrime), -- FK
 CONSTRAINT leiFK FOREIGN KEY (idLei) REFERENCES Lei (codLei) -- FK
);


-- relacionamento de crime com arma
CREATE TABLE IF NOT EXISTS CrimeArma
(idArma integer not null,
 idCrime integer not null,

 CONSTRAINT crimeArmaPK PRIMARY KEY (idArma, idCrime), -- PK
 CONSTRAINT crimeFK FOREIGN KEY (idCrime) REFERENCES Crime (codCrime), -- FK
 CONSTRAINT armaFK FOREIGN KEY (idArma) REFERENCES Arma (codArma) -- FK
);


