create table disciplinas(

    id BIGSERIAL not null,
    unidade_id BIGSERIAL NOT NULL REFERENCES unidades (id),
    serie BIGSERIAL NOT NULL REFERENCES series (id),
    grupo_id BIGSERIAL NOT NULL REFERENCES grupos_disciplinas (id),
    nome VARCHAR(100) NOT NULL,
    carga_horaria VARCHAR(100) NOT NULL,

    primary key(id)
);