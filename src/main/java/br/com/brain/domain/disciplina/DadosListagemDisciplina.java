package br.com.brain.domain.disciplina;

import br.com.brain.domain.grupo.GrupoDisciplina;

public record DadosListagemDisciplina(String nome, String cargaHoraria, GrupoDisciplina grupo, String serie, String unidade) {

    public DadosListagemDisciplina(Disciplina disciplina) {
        this(disciplina.getNome(), disciplina.getCargaHoraria(), disciplina.getGrupo(), disciplina.getSerie(),
                disciplina.getUnidade());
    }

}
