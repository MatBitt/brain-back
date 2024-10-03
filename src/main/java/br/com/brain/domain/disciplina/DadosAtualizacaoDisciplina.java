package br.com.brain.domain.disciplina;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDisciplina(
        @NotNull Long id,
        String nome,
        Long unidadeId,
        Long serieId,
        String cargaHoraria,
        Long grupoId) {
}
