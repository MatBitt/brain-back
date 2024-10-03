create table series(

    id BIGSERIAL not null,
    nome VARCHAR(100) NOT NULL UNIQUE,

    primary key(id)
);