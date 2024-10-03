package br.com.brain.domain.disciplina;

public record DadosListagemDisciplina(String nome, String cargaHoraria, Long grupoId, Long serieId, Long unidadeId) {

    public DadosListagemDisciplina(Disciplina disciplina) {
        this(disciplina.getNome(), disciplina.getCargaHoraria(), disciplina.getGrupo().getId(),
                disciplina.getSerie().getId(),
                disciplina.getUnidade().getId());
    }

}
