CREATE TABLE alunos(
    cpf VARCHAR(11) NOT NULL UNIQUE,
    rg VARCHAR(9) NOT NULL UNIQUE,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    nome_social VARCHAR(100) NOT NULL,
    data_de_nascimento date NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    email_escolar VARCHAR(100) NOT NULL UNIQUE,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    cor_raca VARCHAR(100) NOT NULL,
    cidade_naturalidade VARCHAR(100) NOT NULL,
    tipo_sanguineo VARCHAR(3) NOT NULL,

    PRIMARY KEY(cpf)

);