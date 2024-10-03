create table responsaveis(

    cpf varchar (11) not null UNIQUE,
    rg VARCHAR(9) NOT NULL UNIQUE,
    nome varchar(100) not null,
    email varchar(100) not null UNIQUE,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(cpf)

);