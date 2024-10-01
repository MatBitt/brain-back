create table disciplinas(

    id BIGSERIAL not null,
    unidade VARCHAR(100) NOT NULL,
    serie VARCHAR(100) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    carga_horaria VARCHAR(100) NOT NULL,
    grupo_id BIGSERIAL NOT NULL REFERENCES grupos_disciplinas (id),

    primary key(id)
);