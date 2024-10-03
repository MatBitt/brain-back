create table usuarios(

    id BIGSERIAL not null,
    login varchar(100) not null UNIQUE,
    senha varchar(255) not null,

    primary key(id)

);