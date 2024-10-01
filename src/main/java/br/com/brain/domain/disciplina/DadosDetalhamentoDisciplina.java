package br.com.brain.domain.disciplina;

import br.com.brain.domain.grupo.GrupoDisciplina;

public record DadosDetalhamentoDisciplina(String nome, String cargaHoraria, GrupoDisciplina grupo, String serie, String unidade) {

    public DadosDetalhamentoDisciplina(Disciplina disciplina) {
        this(disciplina.getNome(), disciplina.getCargaHoraria(), disciplina.getGrupo(), disciplina.getSerie(),
                disciplina.getUnidade());
    }
}
